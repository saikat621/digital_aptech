package com.digitalaptechapplication.database.table

import androidx.room.*
import com.digitalaptechapplication.database.Constants
import java.io.Serializable

@Entity(tableName = Constants.USER_TABLE)
class UserEntity: Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.USER_ID)
    var id: Int = 0

    @ColumnInfo(name = Constants.USER_NAME)
    var name: String? = null

    @ColumnInfo(name = Constants.USER_ADDRRESS)
    var address: String? = null

    @ColumnInfo(name = Constants.USER_EMAIL)
    var email: String? = null

    @ColumnInfo(name = Constants.USER_PHONE)
    var phone: String? = null
}


@Dao
interface UserDao {

    @Insert
    fun insert(vararg user: UserEntity)

    @Query("DELETE FROM ${Constants.USER_TABLE}")
    fun deleteAll()

    @Query("SELECT * FROM ${Constants.USER_TABLE} order by ${Constants.USER_NAME}")
    fun getAll(): List<UserEntity>
}