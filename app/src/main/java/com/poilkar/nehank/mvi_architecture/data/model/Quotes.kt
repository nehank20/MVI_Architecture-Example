package com.poilkar.nehank.mvi_architecture.data.model


import com.google.gson.annotations.SerializedName

data class Quotes(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
) {
    data class Result(
        val author: String,
        val authorSlug: String,
        val content: String,
        val dateAdded: String,
        val dateModified: String,
        @SerializedName("_id")
        val id: String,
        val length: Int,
        val tags: List<String>
    )
}