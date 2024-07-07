package io.github.shvmsaini.nextdns.service.model

import com.google.gson.annotations.SerializedName

data class LogsModel(
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
    @SerializedName("meta") var meta: Meta? = Meta()
) {
    data class Data(
        @SerializedName("timestamp") var timestamp: String? = null,
        @SerializedName("domain") var domain: String? = null,
        @SerializedName("root") var root: String? = null,
        @SerializedName("encrypted") var encrypted: Boolean? = null,
        @SerializedName("protocol") var protocol: String? = null,
        @SerializedName("clientIp") var clientIp: String? = null,
        @SerializedName("status") var status: String? = null,
        @SerializedName("reasons") var reasons: ArrayList<Reason> = arrayListOf()
    ) {
        data class Reason(
            @SerializedName("id") var id: String? = null,
            @SerializedName("name") var name: String? = null
        )
    }

    data class Meta(
        @SerializedName("pagination") var pagination: Pagination? = Pagination(),
        @SerializedName("stream") var stream: Stream? = Stream()
    ) {
        data class Stream(
            @SerializedName("id") var id: String? = null
        )

        data class Pagination(
            @SerializedName("cursor") var cursor: String? = null
        )
    }
}
