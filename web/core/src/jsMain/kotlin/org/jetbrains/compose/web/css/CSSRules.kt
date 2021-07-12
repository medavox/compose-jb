package org.jetbrains.compose.web.css

import org.jetbrains.compose.web.css.selectors.CSSSelector


open class CSSRuleBuilderImpl : StyleBuilderImpl()

@Suppress("EqualsOrHashCode")
interface CSSRuleDeclaration {
    val header: String

    override fun equals(other: Any?): Boolean
}

interface CSSStyledRuleDeclaration {
    val style: StyleHolder
}

data class CSSStyleRuleDeclaration(
    val selector: CSSSelector,
    override val style: StyleHolder
) : CSSRuleDeclaration, CSSStyledRuleDeclaration {
    override val header
        get() = selector.toString()
}

interface CSSGroupingRuleDeclaration: CSSRuleDeclaration {
    val rules: CSSRuleDeclarationList
}

typealias CSSRuleDeclarationList = List<CSSRuleDeclaration>
typealias MutableCSSRuleDeclarationList = MutableList<CSSRuleDeclaration>

fun buildCSSStyleRule(cssRule: StyleBuilder.() -> Unit): StyleHolder {
    val builder = CSSRuleBuilderImpl()
    builder.cssRule()
    return builder
}
