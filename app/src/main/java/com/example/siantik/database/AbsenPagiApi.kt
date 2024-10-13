import com.example.siantik.database.AbsenPagiResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AbsenPagiApi {
    @Multipart
    @POST("absen-pagi")
    fun submitAbsenPagi(
        @Part("nrpNip") nrpNip: RequestBody,
        @Part("nama") nama: RequestBody,
        @Part("lokasiId") lokasiId: RequestBody,
        @Part("keterangan") keterangan: RequestBody,
        @Part("waktu") waktu: RequestBody,
        @Part bukti: MultipartBody.Part // Parameter untuk file gambar
    ): Call<AbsenPagiResponse>
}
