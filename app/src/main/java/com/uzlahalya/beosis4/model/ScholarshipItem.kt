import com.google.gson.annotations.SerializedName

data class ScholarshipItem(

    @field:SerializedName("continent")
    val continent: String? = null,

    @field:SerializedName("benefits")
    val benefits: String? = null,

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("requirements")
    val requirements: String? = null,

    @field:SerializedName("scholarship_name")
    val scholarshipName: String? = null,

    @field:SerializedName("close_register")
    val closeRegister: String? = null,

    @field:SerializedName("degree")
    val degree: String? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("open_register")
    val openRegister: String? = null,

    @field:SerializedName("university_name")
    val universityName: String? = null,

    @field:SerializedName("major")
    val major: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)
