package rut.miit.simpleapp.viewmodels

import GameOfThronesApi
import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rut.miit.simpleapp.db.CharacterDatabase
import rut.miit.simpleapp.db.CharacterEntity
import rut.miit.simpleapp.db.CharacterRepository

class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CharacterRepository
    val allCharacters: LiveData<List<CharacterEntity>>

    init {
        val characterDao = CharacterDatabase.getDatabase(application).characterDao()
        repository = CharacterRepository(characterDao)
        allCharacters = repository.readAllCharacters.asLiveData()
    }

    fun fetchAndSaveCharacters(page: Int, pageSize: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiCharacters = GameOfThronesApi.getCharacters(page, pageSize)
                val dbCharacters = apiCharacters.map {
                    CharacterEntity(
                        name = it.name,
                        culture = it.culture,
                        born = it.born,
                        titles = it.titles.joinToString(", "),
                        aliases = it.aliases.joinToString(", "),
                        playedBy = it.playedBy.joinToString(", ")
                    )
                }
                repository.replaceCharacters(dbCharacters)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun getCharacterById(id: Int): LiveData<List<CharacterEntity>> {
        return repository.getCharacterById(id).asLiveData()
    }
}
