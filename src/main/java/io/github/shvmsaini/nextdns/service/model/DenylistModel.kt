package io.github.shvmsaini.nextdns.service.model

import com.google.gson.annotations.SerializedName

data class DenylistModel(
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
) {
    data class Data(
        @SerializedName("id") var id: String? = null,
        @SerializedName("active") var active: Boolean? = null,
    )
}
