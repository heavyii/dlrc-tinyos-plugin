/*
 * Dlrc 2, NesC development in Eclipse.
 * Copyright (C) 2009 DLRC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Web:  http://tos-ide.ethz.ch
 * Mail: tos-ide@tik.ee.ethz.ch
 */
package tinyos.dlrc.nesc12.collector;
import tinyos.dlrc.nesc12.collector.actions.*;

public class ParserActionRepository{
	public static ParserAction[] cases(){
		ParserAction[] result = new ParserAction[]{
			new Action0(),
			new Action1(),
			new Action2(),
			new Action3(),
			new Action4(),
			new Action5(),
			new Action6(),
			new Action7(),
			new Action8(),
			new Action9(),
			new Action10(),
			new Action11(),
			new Action12(),
			new Action13(),
			new Action14(),
			new Action15(),
			new Action16(),
			new Action17(),
			new Action18(),
			new Action19(),
			new Action20(),
			new Action21(),
			new Action22(),
			new Action23(),
			new Action24(),
			new Action25(),
			new Action26(),
			new Action27(),
			new Action28(),
			new Action29(),
			new Action30(),
			new Action31(),
			new Action32(),
			new Action33(),
			new Action34(),
			new Action35(),
			new Action36(),
			new Action37(),
			new Action38(),
			new Action39(),
			new Action40(),
			new Action41(),
			new Action42(),
			new Action43(),
			new Action44(),
			new Action45(),
			new Action46(),
			new Action47(),
			new Action48(),
			new Action49(),
			new Action50(),
			new Action51(),
			new Action52(),
			new Action53(),
			new Action54(),
			new Action55(),
			new Action56(),
			new Action57(),
			new Action58(),
			new Action59(),
			new Action60(),
			new Action61(),
			new Action62(),
			new Action63(),
			new Action64(),
			new Action65(),
			new Action66(),
			new Action67(),
			new Action68(),
			new Action69(),
			new Action70(),
			new Action71(),
			new Action72(),
			new Action73(),
			new Action74(),
			new Action75(),
			new Action76(),
			new Action77(),
			new Action78(),
			new Action79(),
			new Action80(),
			new Action81(),
			new Action82(),
			new Action83(),
			new Action84(),
			new Action85(),
			new Action86(),
			new Action87(),
			new Action88(),
			new Action89(),
			new Action90(),
			new Action91(),
			new Action92(),
			new Action93(),
			new Action94(),
			new Action95(),
			new Action96(),
			new Action97(),
			new Action98(),
			new Action99(),
			new Action100(),
			new Action101(),
			new Action102(),
			new Action103(),
			new Action104(),
			new Action105(),
			new Action106(),
			new Action107(),
			new Action108(),
			new Action109(),
			new Action110(),
			new Action111(),
			new Action112(),
			new Action113(),
			new Action114(),
			new Action115(),
			new Action116(),
			new Action117(),
			new Action118(),
			new Action119(),
			new Action120(),
			new Action121(),
			new Action122(),
			new Action123(),
			new Action124(),
			new Action125(),
			new Action126(),
			new Action127(),
			new Action128(),
			new Action129(),
			new Action130(),
			new Action131(),
			new Action132(),
			new Action133(),
			new Action134(),
			new Action135(),
			new Action136(),
			new Action137(),
			new Action138(),
			new Action139(),
			new Action140(),
			new Action141(),
			new Action142(),
			new Action143(),
			new Action144(),
			new Action145(),
			new Action146(),
			new Action147(),
			new Action148(),
			new Action149(),
			new Action150(),
			new Action151(),
			new Action152(),
			new Action153(),
			new Action154(),
			new Action155(),
			new Action156(),
			new Action157(),
			new Action158(),
			new Action159(),
			new Action160(),
			new Action161(),
			new Action162(),
			new Action163(),
			new Action164(),
			new Action165(),
			new Action166(),
			new Action167(),
			new Action168(),
			new Action169(),
			new Action170(),
			new Action171(),
			new Action172(),
			new Action173(),
			new Action174(),
			new Action175(),
			new Action176(),
			new Action177(),
			new Action178(),
			new Action179(),
			new Action180(),
			new Action181(),
			new Action182(),
			new Action183(),
			new Action184(),
			new Action185(),
			new Action186(),
			new Action187(),
			new Action188(),
			new Action189(),
			new Action190(),
			new Action191(),
			new Action192(),
			new Action193(),
			new Action194(),
			new Action195(),
			new Action196(),
			new Action197(),
			new Action198(),
			new Action199(),
			new Action200(),
			new Action201(),
			new Action202(),
			new Action203(),
			new Action204(),
			new Action205(),
			new Action206(),
			new Action207(),
			new Action208(),
			new Action209(),
			new Action210(),
			new Action211(),
			new Action212(),
			new Action213(),
			new Action214(),
			new Action215(),
			new Action216(),
			new Action217(),
			new Action218(),
			new Action219(),
			new Action220(),
			new Action221(),
			new Action222(),
			new Action223(),
			new Action224(),
			new Action225(),
			new Action226(),
			new Action227(),
			new Action228(),
			new Action229(),
			new Action230(),
			new Action231(),
			new Action232(),
			new Action233(),
			new Action234(),
			new Action235(),
			new Action236(),
			new Action237(),
			new Action238(),
			new Action239(),
			new Action240(),
			new Action241(),
			new Action242(),
			new Action243(),
			new Action244(),
			new Action245(),
			new Action246(),
			new Action247(),
			new Action248(),
			new Action249(),
			new Action250(),
			new Action251(),
			new Action252(),
			new Action253(),
			new Action254(),
			new Action255(),
			new Action256(),
			new Action257(),
			new Action258(),
			new Action259(),
			new Action260(),
			new Action261(),
			new Action262(),
			new Action263(),
			new Action264(),
			new Action265(),
			new Action266(),
			new Action267(),
			new Action268(),
			new Action269(),
			new Action270(),
			new Action271(),
			new Action272(),
			new Action273(),
			new Action274(),
			new Action275(),
			new Action276(),
			new Action277(),
			new Action278(),
			new Action279(),
			new Action280(),
			new Action281(),
			new Action282(),
			new Action283(),
			new Action284(),
			new Action285(),
			new Action286(),
			new Action287(),
			new Action288(),
			new Action289(),
			new Action290(),
			new Action291(),
			new Action292(),
			new Action293(),
			new Action294(),
			new Action295(),
			new Action296(),
			new Action297(),
			new Action298(),
			new Action299(),
			new Action300(),
			new Action301(),
			new Action302(),
			new Action303(),
			new Action304(),
			new Action305(),
			new Action306(),
			new Action307(),
			new Action308(),
			new Action309(),
			new Action310(),
			new Action311(),
			new Action312(),
			new Action313(),
			new Action314(),
			new Action315(),
			new Action316(),
			new Action317(),
			new Action318(),
			new Action319(),
			new Action320(),
			new Action321(),
			new Action322(),
			new Action323(),
			new Action324(),
			new Action325(),
			new Action326(),
			new Action327(),
			new Action328(),
			new Action329(),
			new Action330(),
			new Action331(),
			new Action332(),
			new Action333(),
			new Action334(),
			new Action335(),
			new Action336(),
			new Action337(),
			new Action338(),
			new Action339(),
			new Action340(),
			new Action341(),
			new Action342(),
			new Action343(),
			new Action344(),
			new Action345(),
			new Action346(),
			new Action347(),
			new Action348(),
			new Action349(),
			new Action350(),
			new Action351(),
			new Action352(),
			new Action353(),
			new Action354(),
			new Action355(),
			new Action356(),
			new Action357(),
			new Action358(),
			new Action359(),
			new Action360(),
			new Action361(),
			new Action362(),
			new Action363(),
			new Action364(),
			new Action365(),
			new Action366(),
			new Action367(),
			new Action368(),
			new Action369(),
			new Action370(),
			new Action371(),
			new Action372(),
			new Action373(),
			new Action374(),
			new Action375(),
			new Action376(),
			new Action377(),
			new Action378(),
			new Action379(),
			new Action380(),
			new Action381(),
			new Action382(),
			new Action383(),
			new Action384(),
			new Action385(),
			new Action386(),
			new Action387(),
			new Action388(),
			new Action389(),
			new Action390(),
			new Action391(),
			new Action392(),
			new Action393(),
			new Action394(),
			new Action395(),
			new Action396(),
			new Action397(),
			new Action398(),
			new Action399(),
			new Action400(),
			new Action401(),
			new Action402(),
			new Action403(),
			new Action404(),
			new Action405(),
			new Action406(),
			new Action407(),
			new Action408(),
			new Action409(),
			new Action410(),
			new Action411(),
			new Action412(),
			new Action413(),
			new Action414(),
			new Action415(),
			new Action416(),
			new Action417(),
			new Action418(),
			new Action419(),
			new Action420(),
			new Action421(),
			new Action422(),
			new Action423(),
			new Action424(),
			new Action425(),
			new Action426(),
			new Action427(),
			new Action428(),
			new Action429(),
			new Action430(),
			new Action431(),
			new Action432(),
			new Action433(),
			new Action434(),
			new Action435(),
			new Action436(),
			new Action437(),
			new Action438(),
			new Action439(),
			new Action440(),
			new Action441(),
			new Action442(),
			new Action443(),
			new Action444(),
			new Action445(),
			new Action446(),
			new Action447(),
			new Action448(),
			new Action449(),
			new Action450(),
			new Action451(),
			new Action452(),
			new Action453(),
			new Action454(),
			new Action455(),
			new Action456(),
			new Action457(),
			new Action458(),
			new Action459(),
			new Action460(),
			new Action461(),
			new Action462(),
			new Action463(),
			new Action464(),
			new Action465(),
			new Action466(),
			new Action467(),
			new Action468(),
			new Action469(),
			new Action470(),
			new Action471(),
			new Action472(),
			new Action473(),
			new Action474(),
			new Action475(),
			new Action476(),
			new Action477(),
			new Action478(),
			new Action479(),
			new Action480(),
			new Action481(),
			new Action482(),
			new Action483(),
			new Action484(),
			new Action485(),
			new Action486(),
			new Action487(),
			new Action488(),
			new Action489(),
			new Action490(),
			new Action491(),
			new Action492(),
			new Action493(),
			new Action494(),
			new Action495(),
			new Action496(),
			new Action497(),
			new Action498(),
			new Action499(),
			new Action500(),
			new Action501(),
			new Action502(),
			new Action503(),
			new Action504(),
			new Action505(),
			new Action506(),
			new Action507(),
			new Action508(),
			new Action509(),
			new Action510(),
			new Action511(),
			new Action512(),
			new Action513(),
			new Action514(),
			new Action515(),
			new Action516(),
			new Action517(),
			new Action518(),
			new Action519(),
			new Action520(),
			new Action521(),
			new Action522(),
			new Action523(),
			new Action524(),
			new Action525(),
			new Action526(),
			new Action527(),
			new Action528(),
			new Action529(),
			new Action530(),
			new Action531(),
			new Action532(),
			new Action533(),
			new Action534(),
			new Action535(),
			new Action536(),
			new Action537(),
			new Action538(),
			new Action539(),
			new Action540(),
			new Action541(),
			new Action542(),
			new Action543(),
			new Action544(),
			new Action545(),
			new Action546(),
			new Action547(),
			new Action548(),
			new Action549(),
			new Action550(),
			new Action551(),
			new Action552(),
			new Action553(),
			new Action554(),
			new Action555(),
			new Action556(),
			new Action557(),
			new Action558(),
			new Action559(),
			new Action560(),
			new Action561(),
			new Action562(),
			new Action563(),
			new Action564(),
			new Action565(),
			new Action566(),
			new Action567(),
			new Action568(),
			new Action569(),
			new Action570(),
			new Action571(),
			new Action572(),
			new Action573(),
			new Action574(),
			new Action575(),
			new Action576(),
			new Action577(),
			new Action578(),
			new Action579(),
			new Action580(),
			new Action581(),
			new Action582(),
			new Action583(),
			new Action584(),
			new Action585(),
			new Action586(),
			new Action587(),
			new Action588(),
			new Action589(),
			new Action590(),
			new Action591(),
			new Action592(),
			new Action593(),
			new Action594(),
			new Action595(),
			new Action596(),
			new Action597(),
			new Action598(),
			new Action599(),
			new Action600(),
			new Action601(),
			new Action602(),
			new Action603(),
			new Action604(),
			new Action605(),
			new Action606(),
			new Action607(),
			new Action608(),
			new Action609(),
			new Action610(),
			new Action611(),
			new Action612(),
			new Action613(),
			new Action614(),
			new Action615(),
			new Action616(),
			new Action617(),
			new Action618(),
			new Action619(),
			new Action620(),
			new Action621(),
			new Action622(),
			new Action623(),
			new Action624(),
			new Action625(),
			new Action626(),
			new Action627(),
			new Action628(),
			new Action629(),
			new Action630(),
			new Action631(),
			new Action632(),
			new Action633(),
			new Action634(),
			new Action635(),
			new Action636(),
			new Action637(),
			new Action638(),
			new Action639(),
			new Action640(),
			new Action641(),
			new Action642(),
			new Action643(),
			new Action644(),
			new Action645(),
			new Action646(),
			new Action647(),
			new Action648(),
			new Action649(),
			new Action650(),
			new Action651(),
			new Action652(),
			new Action653(),
			new Action654(),
			new Action655(),
			new Action656(),
			new Action657(),
			new Action658(),
			new Action659(),
			new Action660(),
			new Action661(),
			new Action662(),
			new Action663(),
			new Action664(),
			new Action665(),
			new Action666(),
			new Action667(),
			new Action668(),
			new Action669(),
			new Action670(),
			new Action671(),
			new Action672(),
			new Action673(),
			new Action674(),
			new Action675(),
			new Action676(),
			new Action677(),
			new Action678(),
			new Action679(),
			new Action680(),
			new Action681(),
			new Action682(),
			new Action683(),
			new Action684(),
			new Action685(),
			new Action686(),
			new Action687(),
			new Action688(),
			new Action689(),
			new Action690(),
			new Action691(),
			new Action692(),
			new Action693(),
			new Action694(),
			new Action695(),
			new Action696(),
			new Action697(),
			new Action698(),
			new Action699(),
			new Action700(),
			new Action701(),
			new Action702(),
			new Action703(),
			new Action704(),
			new Action705(),
			new Action706(),
			new Action707(),
			new Action708(),
			new Action709(),
			new Action710(),
			new Action711(),
			new Action712(),
			new Action713(),
			new Action714(),
			new Action715(),
			new Action716(),
			new Action717(),
			new Action718(),
			new Action719(),
			new Action720(),
			new Action721(),
			new Action722(),
			new Action723(),
			new Action724(),
			new Action725(),
			new Action726(),
			new Action727(),
			new Action728(),
			new Action729(),
			new Action730(),
			new Action731(),
			new Action732(),
			new Action733(),
			new Action734(),
			new Action735(),
			new Action736(),
			new Action737(),
			new Action738(),
			new Action739(),
			new Action740(),
			new Action741(),
			new Action742(),
			new Action743(),
			new Action744(),
			new Action745(),
			new Action746(),
			new Action747(),
			new Action748(),
			new Action749(),
			new Action750(),
			new Action751(),
			new Action752(),
			new Action753(),
			new Action754(),
			new Action755(),
			new Action756(),
			new Action757(),
			new Action758(),
			new Action759(),
			new Action760(),
			new Action761(),
			new Action762(),
			new Action763(),
			new Action764(),
			new Action765(),
			new Action766(),
			new Action767(),
			new Action768(),
			new Action769(),
			new Action770(),
			new Action771(),
			new Action772(),
			new Action773(),
			new Action774(),
			new Action775(),
			new Action776(),
			new Action777(),
			new Action778(),
			new Action779(),
			new Action780(),
			new Action781(),
			new Action782(),
			new Action783(),
			new Action784(),
			new Action785(),
			new Action786(),
			new Action787(),
			new Action788(),
			new Action789(),
			new Action790(),
			new Action791(),
			new Action792(),
			new Action793(),
			new Action794(),
			new Action795(),
			new Action796(),
			new Action797(),
			new Action798(),
			new Action799(),
			new Action800(),
			new Action801(),
			new Action802(),
			new Action803(),
			new Action804(),
			new Action805(),
			new Action806(),
			new Action807(),
			new Action808(),
			new Action809(),
			new Action810(),
			new Action811(),
			new Action812(),
			new Action813(),
			new Action814(),
			new Action815(),
			new Action816(),
			new Action817(),
			new Action818(),
			new Action819(),
			new Action820(),
			new Action821(),
			new Action822(),
			new Action823(),
			new Action824(),
			new Action825(),
			new Action826(),
			new Action827(),
			new Action828(),
			new Action829(),
			new Action830(),
			new Action831(),
			new Action832(),
			new Action833(),
			new Action834(),
			new Action835(),
			new Action836(),
			new Action837(),
			new Action838(),
			new Action839(),
			new Action840(),
			new Action841(),
			new Action842(),
			new Action843(),
			new Action844(),
			new Action845(),
			new Action846(),
			new Action847(),
			new Action848(),
			new Action849(),
			new Action850(),
			new Action851(),
			new Action852(),
			new Action853(),
			new Action854(),
			new Action855(),
			new Action856(),
			new Action857(),
			new Action858(),
			new Action859(),
			new Action860(),
			new Action861(),
			new Action862(),
			new Action863(),
			new Action864(),
			new Action865(),
			new Action866(),
			new Action867(),
			new Action868(),
			new Action869(),
			new Action870(),
			new Action871(),
			new Action872(),
			new Action873(),
			new Action874(),
			new Action875(),
			new Action876(),
			new Action877(),
			new Action878(),
			new Action879(),
			new Action880(),
			new Action881(),
			new Action882(),
			new Action883(),
			new Action884(),
			new Action885(),
			new Action886(),
			new Action887(),
			new Action888(),
			new Action889(),
			new Action890(),
			new Action891(),
			new Action892(),
			new Action893(),
			new Action894(),
			new Action895(),
			new Action896(),
			new Action897(),
			new Action898(),
			new Action899(),
			new Action900(),
			new Action901(),
			new Action902(),
			new Action903(),
			new Action904(),
			new Action905(),
			new Action906(),
			new Action907(),
			new Action908(),
			new Action909(),
			new Action910(),
			new Action911(),
			new Action912(),
			new Action913(),
			new Action914(),
			new Action915(),
			new Action916(),
			new Action917(),
			new Action918(),
			new Action919(),
			new Action920(),
			new Action921(),
			new Action922(),
			new Action923(),
			new Action924(),
			new Action925(),
			new Action926(),
			new Action927(),
			new Action928(),
			new Action929(),
			new Action930(),
			new Action931(),
			new Action932(),
			new Action933(),
			new Action934(),
			new Action935(),
			new Action936(),
			new Action937(),
			new Action938(),
			new Action939(),
			new Action940(),
			new Action941(),
			new Action942(),
			new Action943(),
			new Action944(),
			new Action945(),
			new Action946(),
			new Action947(),
			new Action948(),
			new Action949(),
			new Action950(),
			new Action951(),
			new Action952(),
			new Action953(),
			new Action954(),
			new Action955(),
			new Action956(),
			new Action957(),
			new Action958(),
			new Action959(),
			new Action960(),
			new Action961(),
			new Action962(),
			new Action963(),
			new Action964(),
			new Action965(),
			new Action966(),
			new Action967(),
			new Action968(),
			new Action969(),
			new Action970(),
			new Action971(),
			new Action972(),
			new Action973(),
			new Action974(),
			new Action975(),
			new Action976(),
			new Action977(),
			new Action978(),
			new Action979(),
			new Action980(),
			new Action981(),
			new Action982(),
			new Action983(),
			new Action984(),
			new Action985(),
			new Action986(),
			new Action987(),
			new Action988(),
			new Action989(),
			new Action990(),
			new Action991(),
			new Action992(),
			new Action993(),
			new Action994(),
			new Action995(),
			new Action996(),
			new Action997(),
			new Action998(),
			new Action999(),
			new Action1000(),
			new Action1001(),
			new Action1002(),
			new Action1003(),
			new Action1004(),
			new Action1005(),
			new Action1006(),
			new Action1007(),
			new Action1008(),
			new Action1009(),
			new Action1010(),
			new Action1011(),
			new Action1012(),
			new Action1013(),
			new Action1014(),
			new Action1015(),
			new Action1016(),
			new Action1017(),
			new Action1018(),
			new Action1019(),
			new Action1020(),
			new Action1021(),
			new Action1022(),
			new Action1023(),
			new Action1024(),
			new Action1025(),
			new Action1026(),
			new Action1027(),
			new Action1028(),
			new Action1029(),
			new Action1030(),
			new Action1031(),
			new Action1032(),
			new Action1033(),
			new Action1034(),
			new Action1035(),
			new Action1036(),
			new Action1037(),
			new Action1038(),
			new Action1039(),
			new Action1040(),
			new Action1041(),
			new Action1042(),
			new Action1043(),
			new Action1044(),
			new Action1045(),
			new Action1046(),
			new Action1047(),
			new Action1048(),
			new Action1049(),
			new Action1050(),
			new Action1051(),
			new Action1052(),
			new Action1053(),
			new Action1054(),
			new Action1055(),
			new Action1056(),
			new Action1057(),
			new Action1058(),
			new Action1059(),
			new Action1060(),
			new Action1061(),
			new Action1062(),
			new Action1063(),
			new Action1064(),
			new Action1065(),
			new Action1066(),
			new Action1067(),
			new Action1068(),
			new Action1069(),
			new Action1070(),
			new Action1071(),
			new Action1072(),
			new Action1073(),
			new Action1074(),
			new Action1075(),
			new Action1076(),
			new Action1077(),
			new Action1078(),
			new Action1079(),
			new Action1080(),
			new Action1081(),
			new Action1082(),
			new Action1083(),
			new Action1084(),
			new Action1085(),
			new Action1086(),
			new Action1087(),
			new Action1088(),
			new Action1089(),
		};
		return result;
	}
}
