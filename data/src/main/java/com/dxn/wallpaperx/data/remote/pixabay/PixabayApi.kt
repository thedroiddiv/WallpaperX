package com.dxn.wallpaperx.data.remote.pixabay

import com.dxn.wallpaperx.data.remote.pixabay.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api")
    suspend fun searchImage(
        @Query("key") apiKey: String,
        @Query("q") query: String? = null,
        @Query("page") page: Int = 1,
        @Query("lang") lang: String = "en",
        @Query("id") id: String? = null,
        @Query("image_type") imageType: String? = null,
        @Query("orientation") orientation: String? = "vertical",
        @Query("category") category: String? = null,
    ): SearchResponse

    companion object {
        val categories = arrayOf(
            "backgrounds" to "https://pixabay.com/get/gbf6fdfbf58de05dee88c4ba3cf3eda4572970184054778d46244ca37573a491cc7e84442db6b73fa2bab05410af4d9c2796ed53cc669fa2246de32aa80c6eaeb_640.jpg",
            "fashion" to "https://pixabay.com/get/ga6b6acbe98e98bebd3a04e4fa760095362d634165748506ec4775c030d87dd7581824d6af38b9b6db4c9af4e5656c40930d56fdbacfabc042f437ad3ae16fcc0_640.jpg",
            "nature" to "https://pixabay.com/get/g55d2f15f36395406ae39bd0f6e84a2d448a6e8b60b27747e48863d18c1dff89b5b725a04c8b77d3d87278980007aa984441f0cff7a70f5730e5b791b57e5aea0_640.jpg",
            "science" to "https://pixabay.com/get/g9b0889661b3778fd47163885703dce979e120c7c09973846341ab40030a90b8aa728cf445c8e32c5c49b6c9a37e4077953e7779e7216e72d4d05a7d80352a3c4_640.jpg",
            "education" to "https://pixabay.com/get/g8dff5dc3de242221d61ff4a01d75c325680bcae0721f4ba88629d7865ad63d394664d1ae204ab19d478e21f4ea0969b307e98d6fba9b95246e7b47106be42e1b_640.jpg",
            "feelings" to "https://pixabay.com/get/g4e62e43617cbd05a5a752678575d9be8495f031ce7d7fa32d267c83033279f4e4345e9d2a7d5ab7abe6adb705c9302505e0c1d4c823183a42b5c6d307100c9e2_640.jpg",
            "health" to "https://pixabay.com/get/g1302b143a8aa2d90aced844f9e858506add012ce96c182147152848a62dec8fef445d4bb6650b7fb1c12bd233ede9dfa359ec3a37422736c28f14f87574bf905_640.jpg",
            "people" to "https://pixabay.com/get/g06627122dfb6dd211c49b1aa5afe716eb78041faed9c061dc37d7f3f4bda51776f5928d65a85ffbafb174e77d45bfaee75f8e93eb9b4b88713bf3f268534b391_640.jpg",
            "religion" to "https://pixabay.com/get/g34bb7daf0fbe2ae3023d4b588955eac6c0a20b9d133fcf2898b83fdd47998007c6cf7b7236649a67212032f06123ccffcac2374ba61f0c085f042de80fe1fd00_640.jpg",
            "places" to "https://pixabay.com/get/g4bf76e4c9f3316bed54a1768e2c634d125dc6b6c164cda527bc494ac0b6aa5812bd54e315fe355f84d133c0c39282d2296fc62a31edbcaaa9bf61be00ee38ddd_640.png",
            "animals" to "https://pixabay.com/get/g42376959e5e3d2c4bd138e3b1c8e9eb71161e3a2c131643782596ac85e0fb6ae7a3462dd4c3bd7d3821f86258d3e0764c348439bf1ee0a3e52fe62657df0684d_640.jpg",
            "industry" to "https://pixabay.com/get/g936317a0ba7c9a1816d0b268641d04e2c8145bc6cb178d7dccf791a5cecdb09d4c788f76aec9f07cf723379deebb864be17f5c98a4cfeed32497d2e004eb4b7e_640.jpg",
            "computer" to "https://pixabay.com/get/gb4ddfec0ea7e25675186587683fa039dfb4a512db5b1984b8ab588d987ca50dcb96ffc9ed54ff2708a4dbacceb0c12363cf41c0759e91fedae2041d446025a28_640.jpg",
            "food" to "https://pixabay.com/get/g3d5214e6777641d7ba16ba82ad836bc299f08d5a0942cab8233100fd7c587451f8898da9ccd73e61322d8dcfc39d2b0d89a5343aee6264b59facf1252c88ed0b_640.jpg",
            "sports" to "https://pixabay.com/get/ge570f5a67ff4bbe3479848e0f0d8bdf8f88e91c7cc48e45c31fe9307b20b85b17b0293ddd9b28e1e92a54d90381dddbbeae451d5aebc5ce95764023df3ac95a5_640.jpg",
            "transportation" to "https://pixabay.com/get/g48a177c2073118df9fcfbeaf1718899cfdc13defb6131f8a7836bed46d12074edc8ad71916fe56bca036d702c0636e55d348e2ac6cf329772a0b9ca10982e241_640.png",
            "travel" to "https://pixabay.com/get/g560254f01c715d07108e53f5f9b9eb2e5c2230bfa452a4ab7bc0c83d505672214f66cba33619b4778ca0203bd02cfa398efd8c0a1285de043055f63a21b0a164_640.jpg",
            "buildings" to "https://pixabay.com/get/gfdb306b2614ef64644ff59394cbf49668ad5d72b120b79d72fb9d0150788bf9ca5a99ab8d00396114b08f638bc30b050b7567a8220f25c48c2ddc87ec62130cd_640.jpg",
            "business" to "https://pixabay.com/get/g7735420615764343b826aa5058b81cf2a7db34a1f5186a4ae42fbcc350157c2b0fb27114b374c0fd50619b3afab866bcccceaf8a329c52afd92622e08eb6a542_640.jpg",
            "music" to "https://pixabay.com/get/gc27e75a047b256f774dfb863fd73fdd5bf55afddd3837dfcb2a14486a15425d2067b8bab3da39911cc46aea151d400b20718c96efc424aed6f015b84c61bf334_640.png"
        )
    }
}
