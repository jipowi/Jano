<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<!-- TODO: Auto-generated template -->
		<html>
			<center>
				<table width="90%">
					<col style="width:50%;" />
					<tbody>
						<tr>
							<td colspan="2" style="height:1.0cm;"></td>
						</tr>
						<tr>
							<td colspan="2" align="center" style="font-weight: bold;">
								<h3>
									<xsl:text>FACULTAD DE INGENIERIA CIENCIAS FISICAS Y MATEMATICA UCE</xsl:text>
								</h3>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:1.0cm;"></td>
						</tr>
						<tr>
							<td colspan="2" align="center" style="font-weight: bold;">
								<h3>
									<xsl:text>DEPARTAMENTO FINANCIERO</xsl:text>
								</h3>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:1.0cm;"></td>
						</tr>
						<tr>
							<td colspan="2" align="center" style="font-weight: bold;">
								<h3>
									<xsl:text>CERTIFICACION PRESUPUESTARIA</xsl:text>
								</h3>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:2.0cm;"></td>
						</tr>

						<tr>
							<td colspan="2">
								<br />
								<xsl:text>Quito, </xsl:text>
								<xsl:value-of select="documento/fechaActual" />
								<br />
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:0.8cm;"></td>
						</tr>

						<tr>
							<td colspan="2">
								<br />
								<xsl:text>Beneficiario: </xsl:text>
								<xsl:value-of select="documento/beneficiario" />
								<br />
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:0.8cm;"></td>
						</tr>
						<tr>
							<td colspan="2">

								<xsl:text>Se certifica la disponibilidad de fondos</xsl:text>

							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:0.8cm;"></td>
						</tr>
						<tr>
							<td colspan="2">
								<table width="50%" border="1" cellspacing="0" cellpadding="0"
									class="blanco">
									<col style="width:30%;" />
									<col style="width:50%;" />
									<col style="width:20%;" />
									<tbody>
										<tr align="center">
											<td>
												<xsl:text>CUR</xsl:text>
											</td>
											<td>
												<xsl:text>Partida</xsl:text>
											</td>
											<td>
												<xsl:text>Valor</xsl:text>
											</td>
										</tr>
										<xsl:for-each select="documento/compromisos">
											<tr>
												<td>
													<xsl:value-of select="codigo" />
												</td>
												<td>
													<xsl:value-of select="partida" />
												</td>
												<td>
													<xsl:value-of select="valor" />
												</td>
											</tr>
										</xsl:for-each>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:0.5cm;"></td>
						</tr>
						<tr>
							<td colspan="2">
								<br />
								<xsl:text>Total: </xsl:text>
								<xsl:value-of select="documento/total" />
								<br />
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:0.8cm;"></td>
						</tr>
						<tr align="center">
							<td colspan="2">
								<table width="40%">
									<tr align="center">
										<td>
											<xsl:text>........................</xsl:text>
										</td>
										<td>
											<xsl:text>........................</xsl:text>
										</td>
									</tr>
									<tr align="center">
										<td>
											<xsl:text>Certifica</xsl:text>
										</td>
										<td>
											<xsl:text>Autoriza</xsl:text>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</center>
		</html>
	</xsl:template>
</xsl:stylesheet>