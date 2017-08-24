<?xml version="1.0"?>
<!--
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
-->

<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="1.0">

    <xsl:output indent="no" method="text"/>
    <xsl:strip-space elements="*"/>

    <xsl:param name="filename">-not-set-</xsl:param>
    <xsl:param name="filedir">-not-set-</xsl:param>

    <!-- use the xsl-parameter -->
    <xsl:template match="/">
        filename='<xsl:value-of select="$filename"/>'
        filedir ='<xsl:value-of select="$filedir"/>'
    </xsl:template>

    <!-- delete the raw xml data -->
    <xsl:template match="*"/>

</xsl:stylesheet>