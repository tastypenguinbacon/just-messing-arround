<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:template match="/">
        <orderEntries>
            <xsl:for-each select="module/component/orderEntry">
                <xsl:copy-of select="."/>
            </xsl:for-each>
        </orderEntries>
    </xsl:template>
</xsl:stylesheet>