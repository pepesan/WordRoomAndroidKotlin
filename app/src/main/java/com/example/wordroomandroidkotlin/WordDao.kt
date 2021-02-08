package com.example.wordroomandroidkotlin

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM word_table where id like :id")
    fun getWordById(id: Int): Flow<Word>
    @Update
    fun updateWord(word: Word)
    @Delete
    fun deleteWord(word: Word)
}