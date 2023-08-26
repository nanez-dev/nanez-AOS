package org.techtown.nanez.data.source.remote

import org.techtown.nanez.data.api.home.HomeApi
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeRemoteTestDataSource @Inject constructor() {

    fun getHomeInfo(): Response<HomeApi.Response> {
        return Response.success(
            HomeApi.Response(
                imgList = listOf("https://i.pinimg.com/474x/33/9a/a6/339aa6aa272dc632c40ab2bca2747bd9.jpg",
                    "https://i.pinimg.com/474x/34/0e/be/340ebea14b142b19b3e9fd8a3628d163.jpg",
                    "https://i.pinimg.com/474x/d2/0c/ab/d20cab9d5875438dd45bc42e038d2aae.jpg"
                ),
                horizontal = HomeApi.Horizontal(
                    title = "비 올 때, 향수로 더 산뜻하게!",
                    itemList = listOf(
                        HomeApi.Perfume(name = "아이리스 포르셀라나", volume = "50ml", brandName = "엑스 니힐로", imgUrl = "https://i.pinimg.com/474x/46/bc/f4/46bcf41c23f72b08bcc8fa8260ea5005.jpg", description = "화이트 프리지아 부케향에 이제 막 익은 배의 신선함을 입히고 호박, 파출...화이트 프리지아 부케향에 이제 막 익은 배의 신선함을 입히고 호박, 파출..."),
                        HomeApi.Perfume(name = "아이리스 포르셀라나2", volume = "50ml", brandName = "엑스 니힐로", imgUrl = "https://i.pinimg.com/474x/46/bc/f4/46bcf41c23f72b08bcc8fa8260ea5005.jpg", description = "화이트 프리지아 부케향에 이제 막 익은 배의 신선함을 입히고 호박, 파출...화이트 프리지아 부케향에 이제 막 익은 배의 신선함을 입히고 호박, 파출..."),
                        HomeApi.Perfume(name = "아이리스 포르셀라나3", volume = "50ml", brandName = "엑스 니힐로", imgUrl = "https://i.pinimg.com/474x/46/bc/f4/46bcf41c23f72b08bcc8fa8260ea5005.jpg", description = "화이트 프리지아 부케향에 이제 막 익은 배의 신선함을 입히고 호박, 파출...화이트 프리지아 부케향에 이제 막 익은 배의 신선함을 입히고 호박, 파출...")
                    )
                ),
                mainTitle = HomeApi.MainTitle(title = "어디서 좋은 향이 나네?", subTitle = "오늘 나에게 맞는 향수를 찾아라!"),
                recommend = HomeApi.Recommend(
                    title = "이런 향수는 어떠세요?",
                    itemList = listOf(
                        HomeApi.Perfume(name = "아이리스 포르셀라나", volume = "50ml", brandName = "엑스 니힐로", imgUrl = "https://i.pinimg.com/474x/46/bc/f4/46bcf41c23f72b08bcc8fa8260ea5005.jpg", description = null),
                        HomeApi.Perfume(name = "아이리스 포르셀라나2", volume = "50ml", brandName = "엑스 니힐로", imgUrl = "https://i.pinimg.com/474x/46/bc/f4/46bcf41c23f72b08bcc8fa8260ea5005.jpg", description = null),
                        HomeApi.Perfume(name = "아이리스 포르셀라나3", volume = "50ml", brandName = "엑스 니힐로", imgUrl = "https://i.pinimg.com/474x/46/bc/f4/46bcf41c23f72b08bcc8fa8260ea5005.jpg", description = null),
                        HomeApi.Perfume(name = "아이리스 포르셀라나4", volume = "50ml", brandName = "엑스 니힐로", imgUrl = "https://i.pinimg.com/474x/46/bc/f4/46bcf41c23f72b08bcc8fa8260ea5005.jpg", description = null)
                    )
                ),
                brand = HomeApi.Brand(
                    title = "지금, 사랑받고 있는 브랜드",
                    itemList = listOf(
                        HomeApi.BrandItem(name = "조말론 런던", imgUrl = "https://i.pinimg.com/474x/01/b8/c3/01b8c36aa1aca9a592734ce412dac023.jpg"),
                        HomeApi.BrandItem(name = "조말론 런던2", imgUrl = "https://i.pinimg.com/474x/01/b8/c3/01b8c36aa1aca9a592734ce412dac023.jpg"),
                        HomeApi.BrandItem(name = "조말론 런던3", imgUrl = "https://i.pinimg.com/474x/01/b8/c3/01b8c36aa1aca9a592734ce412dac023.jpg"),
                        HomeApi.BrandItem(name = "조말론 런던4", imgUrl = "https://i.pinimg.com/474x/01/b8/c3/01b8c36aa1aca9a592734ce412dac023.jpg")
                    )
                ),
                accord = HomeApi.Accord(
                    title = "파릇한 5월에 어울리는 어코드",
                    itemList = listOf(
                        HomeApi.AccordItem(name = "Fruity", imgUrl = "https://i.pinimg.com/474x/2e/ac/f2/2eacf2d13bc2a3164da4ddc15c9617f6.jpg"),
                        HomeApi.AccordItem(name = "Fruity2", imgUrl = "https://i.pinimg.com/474x/2e/ac/f2/2eacf2d13bc2a3164da4ddc15c9617f6.jpg"),
                        HomeApi.AccordItem(name = "Fruity3", imgUrl = "https://i.pinimg.com/474x/2e/ac/f2/2eacf2d13bc2a3164da4ddc15c9617f6.jpg")
                    )
                )
            )
        )
    }
}