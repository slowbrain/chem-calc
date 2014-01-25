<%@page import="java.util.Collection"%>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8"
	language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Collections"%>
<%@ page import="pl.edu.pjwstk.chemcalc.code.model.ChemicalElement"%>
<%@ page import="pl.edu.pjwstk.chemcalc.code.model.Formula"%>
<%@ page import="pl.edu.pjwstk.chemcalc.code.model.PeriodicTable"%>
<%@ page import="pl.edu.pjwstk.chemcalc.code.dao.Dao"%>
<%
    Dao dao = Dao.INSTANCE;
    PeriodicTable pt = dao.getPeriodictable();
    List<ChemicalElement> chemelems = new ArrayList<ChemicalElement>();
    List<Formula> formulas = new ArrayList<Formula>();
    String chfm = request.getParameter("chemform");
    String chform = (chfm == null) ? "" : chfm;
    Formula fm = null;
    List<String> tooltips = new ArrayList<String>();
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<title>ChemCalc</title>
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/chemcalc.js"></script>
</head>
<body>
	<%
	    try {
	        chemelems = pt.getChemicalElements();
	        Collections.sort(chemelems);
	        formulas = dao.listFormulas();
	        String w = "";
	        for (ChemicalElement t : chemelems) {
	            tooltips.add(
                        t.getAtomicnumber().toString() + "\n" +
                        t.getSymbol() + "\n" +
                        t.getAtomicmass().toString() + "\n" +
                        t.getName()
                );
            }
	        if (chform != "") {
	            fm = dao.calcFormula(chform);
	        }
	    } catch (Exception e) {
	    }
	%>
	<div id="panels" align="center">
		<div class="headline">Molar Mass Calculator</div>
		<div id="calc" class="module">
			<form id="chemcalc" action="" method="post" accept-charset="utf-8">
				<table id="calc">
					<tbody>
						<tr id="indisplay">
							<td align="right"><label for="Formula">Formula:</label></td>
							<td><input id="formula" type="text" name="chemform"
								maxlength="128" size="128" /></td>
						</tr>
						<tr id="outdisplay">
							<td align="right"><label for="Mass">MM [g/mol]:</label></td>
							<td><input id="result" type="text" name="res"
								value="<%=(fm == null) ? "" : String.format("%.3f", fm.getMass())%>"
								size="8" /></td>
						</tr>
						<tr id="representation">
							<td align="right"><label for="Instance">Instance:</label></td>
							<td><input id="instance" type="text" name="ins"
								value="<%=(fm == null) ? "" : fm.getChemform()%>"
								maxlength="128" size="128" disabled="disabled" /></td>
						</tr>
						<tr id="info">
							<td align="right"><label for="Txt">Info:</label></td>
							<td><input id="message" type="text" name="txt"
								value="<%=(fm == null) ? "" : fm.getResult()%>" maxlength="128"
								size="128" disabled="disabled" /></td>
						</tr>
					</tbody>
				</table>
			</form>
			<!--/chemcalc-->
		</div>
		<div id="table" class="module">
			<table id="periodic">
				<tbody>
					<tr>
                        <td align="center" title="<%=tooltips.get(0).toString()%>"><%=chemelems.get(0).getSymbol()%></td>
                        <%
                            for (int i = 0; i < 16; i++) {
                        %>
                        <td align="center"></td>
                        <%
                            }
                        %>
                        <td align="center" title="<%=tooltips.get(1).toString()%>"><%=chemelems.get(1).getSymbol()%></td>
                    </tr>
                    <tr>
                        <td align="center" title="<%=tooltips.get(2).toString()%>"><%=chemelems.get(2).getSymbol()%></td>
                        <td align="center" title="<%=tooltips.get(3).toString()%>"><%=chemelems.get(3).getSymbol()%></td>
                        <%
                            for (int i = 0; i < 10; i++) {
                        %>
                        <td align="center"></td>
                        <%
                            }
                        %>
                        <%
                            for (int i = 4; i < 10; i++) {
                        %>
                        <td align="center" title="<%=tooltips.get(i).toString()%>"><%=chemelems.get(i).getSymbol()%></td>
                        <%
                            }
                        %>
                    </tr>
					<tr>
						<td align="center" title="<%=tooltips.get(10).toString()%>"><%=chemelems.get(10).getSymbol()%></td>
                        <td align="center" title="<%=tooltips.get(11).toString()%>"><%=chemelems.get(11).getSymbol()%></td>
                        <%
						    for (int i = 0; i < 10; i++) {
						%>
						<td align="center"></td>
						<%
						    }
						%>
						<%
						    for (int i = 12; i < 18; i++) {
						%>
						<td align="center" title="<%=tooltips.get(i).toString()%>"><%=chemelems.get(i).getSymbol()%></td>
						<%
						    }
						%>
					</tr>
					<tr>
						<%
						    for (int i = 18; i < 36; i++) {
						%>
                        <td align="center" title="<%=tooltips.get(i).toString()%>"><%=chemelems.get(i).getSymbol()%></td>
						<%
						    }
						%>
					</tr>
					<tr>
						<%
						    for (int i = 36; i < 54; i++) {
						%>
                        <td align="center" title="<%=tooltips.get(i).toString()%>"><%=chemelems.get(i).getSymbol()%></td>
						<%
						    }
						%>
					</tr>
					<tr>
                        <td align="center" title="<%=tooltips.get(54).toString()%>"><%=chemelems.get(54).getSymbol()%></td>
                        <td align="center" title="<%=tooltips.get(55).toString()%>"><%=chemelems.get(55).getSymbol()%></td>
						<td align="center"></td>
						<%
						    for (int i = 71; i < 86; i++) {
						%>
                        <td align="center" title="<%=tooltips.get(i).toString()%>"><%=chemelems.get(i).getSymbol()%></td>
						<%
						    }
						%>
					</tr>
					<tr>
						<td align="center" title="<%=tooltips.get(86).toString()%>"><%=chemelems.get(86).getSymbol()%></td>
                        <td align="center" title="<%=tooltips.get(87).toString()%>"><%=chemelems.get(87).getSymbol()%></td>
                        <td align="center"></td>
						<%
						    for (int i = 103; i < chemelems.size(); i++) {
						%>
                        <td align="center" title="<%=tooltips.get(i).toString()%>"><%=chemelems.get(i).getSymbol()%></td>
						<%
						    }
						%>
					</tr>
					<tr>
						<td align="center"></td>
						<td align="center"></td>
						<%
						    for (int i = 56; i < 71; i++) {
						%>
						<td align="center" title="<%=tooltips.get(i).toString()%>"><%=chemelems.get(i).getSymbol()%></td>
						<%
						    }
						%>
						<td align="center"></td>
					</tr>
					<tr>
						<td align="center"></td>
						<td align="center"></td>
						<%
						    for (int i = 88; i < 103; i++) {
						%>
						<td align="center" title="<%=tooltips.get(i).toString()%>"><%=chemelems.get(i).getSymbol()%></td>
						<%
						    }
						%>
						<td align="center"></td>
					</tr>
				</tbody>
			</table>
			<!--/periodic-->
		</div>
		<div id="numops" class="panel">
			<%
			    String[] symbols = { "1", "2", "3", "", "∙", ":", "_", "<", ">",
			                         "4", "5", "6", "", "•", "::", "-", "←", "→",
			                         "7", "8", "9", "", "*", ":::", "=", "⇐", "⇒",
			                         "(", "0", ")", "", "{", "[", "≡", "]", "}"
			                       };
			%>
			<table id="operators">
				<tbody>
					<tr>
						<%
						    int j = 0;
						    for (String s : symbols) {
						        j++;
						%>
						<td align="center"><%=s%></td>
						<%
						    if (j % 9 == 0) {
						%>
					</tr>
					<tr>
						<%
						    }
						    }
						%>
					</tr>
				</tbody>
			</table>
			<!--/operators-->
		</div>
		
		
	    <%
	    String[][] examples = {
	            {"", ""},
	            {"C", "one atom of carbon"},
	            {"He", "one atom of helium"},
	            {"H2", "one molecule (two atoms) of hydrogen"},
	            {"O_3", "one molecule (three atoms) of ozone with low index (LaTeX) notation"},
	            {"H-H", "one molecule (two atoms with single bound between) of hydrogen"},
	            {"O=O", "one molecule (two atoms with double bound between) of oxygen"},
	            {"N≡N", "one molecule (two atoms with triple bound between) of nitrogen"},
	            {"H∙H", "one molecule (two atoms with dot separator between) of hydrogen"},
	            {"H:H", "one molecule (two atoms with two electrons between) of hydrogen"},
	            {"O::O", "one molecule (two atoms with four electrons between) of oxygen"},
	            {"N:::N", "one molecule (two atoms with six electrons between) of nitrogen"},
	            {"H&lt;Cl", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"H&larr;Cl", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"H&lArr;Cl", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"H<-Cl", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"H<=Cl", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"H<≡Cl", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"H<--Cl", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"H<==Cl", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"H<≡≡Cl", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"Cl>H", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"Cl&rarr;H", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"Cl&rArr;H", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"Cl->H", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"Cl=>H", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"Cl≡>H", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"Cl-->H", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"Cl==>H", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"Cl≡≡>H", "one molecule of hydrochloric acid with polar covalent bond"},
	            {"H2O", "one molecule of water"},
	            {"H:O:H", "one molecule of water (with pair of electrons between) of hydrogen and oxygen"},
	            {"H-O-H", "one molecule of water (with single bound between) of hydrogen and oxygen"},
	            {"CuSO4", "one molecule of anhydrous copper(II) sulfate"},
	            {"CuSO4∙5H2O", "one molecule of pentahydrate copper(II) sulfate"},
	            {"CuSO4•5H2O", "one molecule of pentahydrate copper(II) sulfate"},
	            {"CuSO4*5H2O", "one molecule of pentahydrate copper(II) sulfate"},
	            {"(CuSO4*5H2O)", "one molecule of pentahydrate copper(II) sulfate in parenthesis"},
	            {"2CuSO4∙5H2O", "two molecules of pentahydrate copper(II) sulfate"},
	            {"(CuSO4∙5H2O)3", "triple molecule of pentahydrate copper(II) sulfate"},
	            {"2(CuSO4∙5H2O)3", "two triple molecules of pentahydrate copper(II) sulfate"},
	            {"Ca(OH)2", "one molecule of limewater"},
	            {"Ca(OH)_2", "one molecule of limewater with low index (LaTeX) notation"},
	            {"Ca3[Al(OH)6]2", "one molecule of tricalciumaluminathexahydrat"},
	            {"Ca_3[Al(OH)_6]_2", "one molecule of tricalciumaluminathexahydrat with low index (LaTeX) notation"},
	            {"C2H5O∙SO2∙OC2H5", "one molecule of diethyl sulfate"},
	            {"C_2H_5O∙SO_2∙OC_2H_5", "one molecule of diethyl sulfate with low index (LaTeX) notation"},
	            {"H2N-N=N-NH2", "one molecule of tetrazene"},
	            {"H_2N-N=N-NH_2", "one molecule of tetrazene with low index (LaTeX) notation"},
	            {"NH2OH∙HCl", "one molecule of hydroxylamine"},
	            {"(NH3OHCl)", "one molecule of hydroxylamine in parenthesis"},
	            {"(NH2)2SO2", "one molecule of sulfamide"},
	            {"3(NH_2)_2SO_2", "three molecules of sulfamide with low index (LaTeX) notation"},
	            {"Na[Al(OH)4]", "one molecule of anhydrous sodium aluminate"},
	            {"{Na[Al(OH)4]}", "one molecule of anhydrous sodium aluminate in parenthesis"},
	            {"2{Na[Al(OH)4]}", "two molecules of anhydrous sodium aluminate in parenthesis"},
	            {"2{Na[Al(OH)4]}2", "two doubles (in parenthesis) molecules of anhydrous sodium aluminate"},
	            {"2{Na[Al(OH)4]}_2", "two doubles (in parenthesis) molecules of anhydrous sodium aluminate with low index (LaTeX) notation"},
	            {"[PtCl2(NH3)2]", "one molecule of azanedichloroplatinum"},
	            {"5[Cr(C6H6)2]", "five molecules of bis(benzene)chromium"},
	            {"{5[Cr(C6H6)2]}", "five molecules of bis(benzene)chromium in parenthesis"},
	            {"[Co(NH3)4(SO4)]Br", "one molecule of bis(benzene)chromium"},
	            {"[(NH3)5Cr-OH-Cr(NH3)5]Cl5", "one molecule of ni-hydroxo-bis[pentaamminechromium(III)] chloride"},
	            {"[(NH3)5Cr-OH-Cr(NH3)5]Cl5∙H2O", "one molecule of ni-hydroxo-bis[pentaamminechromium(III)] chloride monohydrate"},
	            {"7[(NH3)5Cr-OH-Cr(NH3)5]Cl5∙H2O", "seven molecules of ni-hydroxo-bis[pentaamminechromium(III)] chloride monohydrate"},
	            {"KBr∙MgBr2∙6H2O", "one molecule of diethyl sulfate"},
	            {"AuCl3∙HCl", "one molecule of chloroauric acid"},
	            {"H[AuCl4]", "one molecule of chloroauric acid"},
	            {"K4[Fe(CN)6]", "one molecule of potassium ferrocyanide"},
	            {"K4[Fe(CN)6]∙3H2O", "one molecule of potassium ferrocyanide trihydrate"},
	            {"Na2[Fe(CN)5NO]", "one molecule of sodium nitroprusside"},
	            // nonames
	            {"[Ag(NH3)2]Cl", "one molecule of [Ag(NH3)2]Cl"},
	            {"[Cd(NH3)4](OH)2", "one molecule of [Cd(NH3)4](OH)2"},
	            {"(NH4)2[Hg(SCN)4]", "one molecule of (NH4)2[Hg(SCN)4]"},
	            {"(NH4)6[Mo7O24]", "one molecule of (NH4)6[Mo7O24]"},
                {"(NH4)6[Mo7O24]∙4H2O", "one molecule of (NH4)6[Mo7O24]∙4H2O"}
	    };
	    %>
		<div id="buttons" class="panel">
			<table id="trigers">
				<tbody>
					<tr>
						<td align="center">Backspace</td>
						<td align="center">Clear</td>
						<td align="center">Calculate</td>
					</tr>
				</tbody>
			</table>
			<!--/triggers-->
			<label for="Examples">Example:</label>
            <select id="examples" name="example">
            <%
                for (int l=0; l<examples.length; l++) {
            %>
                    <option><%=examples[l][0]%></option>
            <%
                }
            %>
            </select>
		</div>
	</div>
	Some examples:<br />
	<%
        for (int l=1; l<examples.length; l++) {
    %>
        <%=examples[l][0]%> - <%=examples[l][1]%><br />
    <%
        }
    %>

</body>
</html>