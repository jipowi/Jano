<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<!-- TODO: Auto-generated template -->
		<html>
			<center>
				<table width="100%" cellpadding="0" cellspacing="5" border="0">
					<col style="width:80%;" />
					<tbody>
						<tr>
							<td style="height:1.0cm;"></td>
						</tr>
						<tr>
							<td align="center" colspan="2" style="font-weight: bold;">
								<h3>
									<xsl:text>FACULTAD DE INGENIERIA CIENCIAS FISICAS Y MATEMATICA UCE</xsl:text>
								</h3>
							</td>
						</tr>
						<tr>
							<td style="height:1.0cm;"></td>
						</tr>
						<tr>
							<td align="center" colspan="2" style="font-weight: bold;">
								<h3>
									<xsl:text>DEPARTAMENTO FINANCIERO</xsl:text>
								</h3>
							</td>
						</tr>
						<tr>
							<td style="height:1.0cm;"></td>
						</tr>
						<tr>
							<td align="center" colspan="2" style="font-weight: bold;">
								<h3>
									<xsl:text>CERTIFICACION PRESUPUESTARIA</xsl:text>
								</h3>
							</td>
						</tr>
						<tr>
							<td style="height:2.0cm;"></td>
						</tr>

						<tr>
							<td>
								<br />
								<xsl:text>Quito, </xsl:text>
								<xsl:value-of select="documento/fechaActual" />
								<br />
							</td>
						</tr>
						<tr>
							<td style="height:0.8cm;"></td>
						</tr>

						<tr>
							<td>
								<br />
								<xsl:text>Beneficiario: </xsl:text>
								<xsl:value-of select="documento/beneficiario" />
								<br />
							</td>
						</tr>
						<tr>
							<td style="height:0.8cm;"></td>
						</tr>
					</tbody>
				</table>

			</center>
		</html>
	</xsl:template>
</xsl:stylesheet>