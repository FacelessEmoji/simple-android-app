package rut.miit.simpleapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM character_table ORDER BY id ASC")
    fun readAllCharacters(): kotlinx.coroutines.flow.Flow<List<CharacterEntity>> // Используем Flow

    @Query("DELETE FROM character_table")
    fun deleteAllCharacters()

    @Query("SELECT * FROM character_table WHERE id = :id")
    fun getCharacterById(id: Int): kotlinx.coroutines.flow.Flow<List<CharacterEntity>> // Получение по ID

    @Transaction
    fun replaceCharacters(characters: List<CharacterEntity>) {
        deleteAllCharacters()
        insertCharacters(characters)
    }

}
