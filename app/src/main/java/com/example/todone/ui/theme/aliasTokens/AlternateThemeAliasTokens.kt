package com.example.todone.ui.theme.aliasTokens

import androidx.compose.ui.graphics.Color
import com.microsoft.fluentui.theme.token.AliasTokens
import com.microsoft.fluentui.theme.token.FluentAliasTokens
import com.microsoft.fluentui.theme.token.TokenSet

class AlternateThemeAliasTokens  : AliasTokens() {
    override val brandColor: TokenSet<FluentAliasTokens.BrandColorTokens, Color> by lazy {
        TokenSet { token ->
            when (token) {
                FluentAliasTokens.BrandColorTokens.Color10 -> Color(0xFF03160A)
                FluentAliasTokens.BrandColorTokens.Color20 -> Color(0xFF052912)
                FluentAliasTokens.BrandColorTokens.Color30 -> Color(0xFF094624)
                FluentAliasTokens.BrandColorTokens.Color40 -> Color(0xFF0A5325)
                FluentAliasTokens.BrandColorTokens.Color50 -> Color(0xFF0C5F32)
                FluentAliasTokens.BrandColorTokens.Color60 -> Color(0xFF0F703B)
                FluentAliasTokens.BrandColorTokens.Color70 -> Color(0xFF0F7937)
                FluentAliasTokens.BrandColorTokens.Color80 -> Color(0xFF107C41)
                FluentAliasTokens.BrandColorTokens.Color90 -> Color(0xFF218D51)
                FluentAliasTokens.BrandColorTokens.Color100 -> Color(0xFF10893C)
                FluentAliasTokens.BrandColorTokens.Color110 -> Color(0xFF1F954A)
                FluentAliasTokens.BrandColorTokens.Color120 -> Color(0xFF37A660)
                FluentAliasTokens.BrandColorTokens.Color130 -> Color(0xFF55B17E)
                FluentAliasTokens.BrandColorTokens.Color140 -> Color(0xFF60BD82)
                FluentAliasTokens.BrandColorTokens.Color150 -> Color(0xFFA0D8B9)
                FluentAliasTokens.BrandColorTokens.Color160 -> Color(0xFFCAEAD8)
                else -> {
                    super.brandColor[token]
                }
            }
        }
    }
}