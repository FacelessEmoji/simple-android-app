package rut.miit.simpleapp.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object FileManager {

    fun saveToExternalStorage(content: String): Boolean {
        if (!isExternalStorageWritable()) {
            Log.e("FileManager", "Внешнее хранилище недоступно для записи")
            return false
        }

        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "myfile.txt")

        return try {
            val fos = FileOutputStream(file)
            fos.write(content.toByteArray())
            fos.close()
            true
        } catch (e: IOException) {
            Log.e("FileManager", "Ошибка при записи в файл: ${e.message}")
            false
        }
    }

    private fun isExternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return state == Environment.MEDIA_MOUNTED
    }


    fun isFileInExternalStorage(): Boolean {
        val file = File("/storage/emulated/0/Documents/myfile.txt")
        return file.exists()
    }

    fun isFileInInternalStorage(context: Context): Boolean {
        val file = File(context.filesDir, "myfile_backup.txt")
        return file.exists()
    }

    fun readExternalFileContent(): String? {
        val file = File("/storage/emulated/0/Documents/myfile.txt")
        return if (file.exists()) file.readText() else null
    }

    fun saveToInternalStorage(context: Context, content: String?): Boolean {
        if (content == null) return false
        val file = File(context.filesDir, "myfile_backup.txt")
        return try {
            file.writeText(content)
            true
        } catch (e: Exception) {
            Log.e("FileManager", "Error saving to internal storage", e)
            false
        }
    }

    fun deleteFromExternalStorage(): Boolean {
        val file = File("/storage/emulated/0/Documents/myfile.txt")
        return if (file.exists()) file.delete() else false
    }

    fun restoreFromInternalToExternal(context: Context): Boolean {
        val internalFile = File(context.filesDir, "myfile_backup.txt")
        if (internalFile.exists()) {
            val externalFile = File("/storage/emulated/0/Documents/myfile.txt")
            return try {
                externalFile.writeText(internalFile.readText())
                true
            } catch (e: Exception) {
                Log.e("FileManager", "Error restoring file", e)
                false
            }
        }
        return false
    }
}
