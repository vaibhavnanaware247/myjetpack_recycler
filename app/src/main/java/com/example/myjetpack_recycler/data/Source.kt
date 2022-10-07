package com.example.myjetpack_recycler.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Source (

  @SerializedName("id"   ) var id   : String? = null,
  @SerializedName("name" ) var name : String? = null

):Serializable