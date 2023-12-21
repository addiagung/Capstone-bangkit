package com.amanda.medicare.data.response

import com.google.gson.annotations.SerializedName

data class DataResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("status")
	val status: Status
)

data class DataItem(

	@field:SerializedName("manfaat")
	val manfaat: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("link_gambar")
	val linkGambar: String,

	@field:SerializedName("efek_samping")
	val efekSamping: String,

	@field:SerializedName("kategori")
	val kategori: String,

	@field:SerializedName("deskripsi")
	val deskripsi: String
)

data class Status(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("message")
	val message: String
)
