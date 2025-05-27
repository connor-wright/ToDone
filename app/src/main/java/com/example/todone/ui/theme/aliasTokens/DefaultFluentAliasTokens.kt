package com.example.todone.ui.theme.aliasTokens

import androidx.compose.ui.graphics.Color
import com.microsoft.fluentui.theme.token.AliasTokens
import com.microsoft.fluentui.theme.token.FluentAliasTokens
import com.microsoft.fluentui.theme.token.TokenSet

class DefaultFluentAliasTokens : AliasTokens() {
    override val brandColor: TokenSet<FluentAliasTokens.BrandColorTokens, Color> by lazy {
        TokenSet { token ->
            when (token) {
                FluentAliasTokens.BrandColorTokens.Color10 -> Color(0xFF443168)
                FluentAliasTokens.BrandColorTokens.Color20 -> Color(0xFF584183)
                FluentAliasTokens.BrandColorTokens.Color30 -> Color(0xFF430E60)
                FluentAliasTokens.BrandColorTokens.Color40 -> Color(0xFF7B64A3)
                FluentAliasTokens.BrandColorTokens.Color50 -> Color(0xFF5B1382)
                FluentAliasTokens.BrandColorTokens.Color60 -> Color(0xFF6C179A)
                FluentAliasTokens.BrandColorTokens.Color70 -> Color(0xFF9D87C4)
                FluentAliasTokens.BrandColorTokens.Color80 -> Color(0xFF7719AA)
                FluentAliasTokens.BrandColorTokens.Color90 -> Color(0xFF862EB5)
                FluentAliasTokens.BrandColorTokens.Color100 -> Color(0xFFC0AAE4)
                FluentAliasTokens.BrandColorTokens.Color110 -> Color(0xFFCEBBED)
                FluentAliasTokens.BrandColorTokens.Color120 -> Color(0xFFDCCDF6)
                FluentAliasTokens.BrandColorTokens.Color130 -> Color(0xFFA864CD)
                FluentAliasTokens.BrandColorTokens.Color140 -> Color(0xFFEADEFF)
                FluentAliasTokens.BrandColorTokens.Color150 -> Color(0xFFD1ABE6)
                FluentAliasTokens.BrandColorTokens.Color160 -> Color(0xFFE6D1F2)
                else -> {
                    super.brandColor[token]
                }
            }
        }
    }
}