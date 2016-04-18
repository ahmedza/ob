package com.fixm.pulser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fixm.pulser.converter.AtsToFixm;

/**
 * Fieldtype 	Data
 *	3 			Message type, number and reference data 
 *	5 			Description of emergency
 *	7			Aircraft identification and SSR mode and code
 *	8			Flight rules and type of flight
 *	9			Number and type of aircraft and wake turbulence category
 *	10			Equipment and capabilities
 *	13			Departure aerodrome and time
 *	14			Estimate data
 *	15			Route
 *	16			Destination aerodrome and total estimated elapsed time, destination alternate aerodrome(s)
 *	17			Arrival aerodrome and time			
 *	18			Other information
 *	19			Supplementary information
 *	20			Alerting search and rescue information
 *	21			Radio failure information
 *	22			Amendment
 **/
public class AtsParse{

	AtsMessage atsMsg = new AtsMessage();
	
    String rcvdMsg = "FPL-H2G2/A5100-YX"+
						"-12ZZZZ/M-YGSDHIJ1J5RVWXYZ/B2D1L"+
						"-ZZZZ1030"+
						"-M066A120 DCT BN V179 TAM DCT 25S146E VFR DUB108041 VFR DCT TAVEV015015 DCT 23S140E 22S138E/N0492F340 LN/N9988F111 IFR DCT GIBLI 20S134E IFR 1712S12936E G326 C/NYOMA/N0494F360F380 "+
						"G326 C/PAGAI/N0489F380PLUS DCT"+
						"-ZZZZ0110 ENBR ZZZZ"+
						"-STS/ALTRV HAZMAT STATE PBN/A1L1B1C1D1O1S2 NAV/RNP2 COM/IRIDIUM88164147619 SUB ETHA DAT/SHV SUR/TCAS DEP/BARNARDS STAR 1215N35912W DEST/AS231042 DOF/120601 REG/TITANIC "+
						"EET/NZZO0029 38S162W0102 ENTRA3021200156 ENTRA0238 SEL/BPKS TYP/VOGON CONSTRUCTOR FLEET CODE/F10042 DLE/TAM0010 TAVEV0150150015 1712S12936E0018 OPR/TRANS STELLAR SPACE"+
						"LINES ORGN/MEGADODO PER/H ALTN/ZZ9 PLURAL Z ALPHA "+
						" RALT/BRONTITALL TALT/EGLL URSA MINOR RIF/22N170W 1815N15330W KSFO RMK/DONT PANIC FROGSTAR IN PURSUIT "+
						"-E/2359 P/999 R/UVE S/PDMJ J/LFUV D/1 42 C GOLD A/BLACKER THAN BLACK N/TOWEL BABEL FISH C/TRILLIAN "
						+ "–USAF LGGGZAZX 1022 126.7 GN 1022 PILOT REPORT OVER NDB ATS UNITS ATHENS FIR ALERTED NIL  )";
    /*"FPL-QTR933-IYS"+
    					"-B77W/H-SDE1E3FGHIJ3J4J5M1RWXY/SB1D1"+
						"-RPLL0950"+
						"-M083F280 DCT MIA L628 ARESI/N0512F280 L628 PCA G474 PLK/N0499F340"+
						"G474 BKK/N0509F300 L301 SADUS/N0506F320 L301 MEPOK/N0499F340 L301"+
						"BUSBO L505 NOBAT L301 LADOT/N0494F360 L301 RAGMA N571 ATBOR P699"+
						"KISAG M430 ASKOP/N0467F220 M430 TOSNA UM430 BOVIP Q215 AFNAN"+
						"-OTHH0904 OTBD DCFH"+
						"-PBN/A1B1D1L1O1S1T1 DOF/160210 REG/A7BAX EET/VVTS0056 VDPP0143 "+
						"VTBB0217 VYYF0246 VECF0338 VOMF0438 VABF0539 OOMM0719 OMAE0821 "+
     					"OBBB0854 SEL/CKFL CODE/06A13A OPR/QTR RMK/TCAS ";*//*"(FPL-H2G2/A5100-YX"+
						"-42ZZZZ/M-GSDHIJ1J5RVWXYZ/B2D1L"+
						"-ZZZZ1030"+
						"-N0494F320 DCT BN V179 TAM DCT 25S146E VFR DCT TAVEV015015 DCT 23S140E"+
						"22S138E/N0492F340 DCT GIBLI 20S134E IFR 1712S12936E G326 C/NYOMA/N0494F360F380"+
						"G326 C/PAGAI/N0489F380PLUS DCT"+
						"-ZZZZ0110 ENBR ZZZZ"+
						"-STS/ALTRV HAZMAT STATE PBN/A1L1B1C1D1O1S2 NAV/RNP2 COM/IRIDIUM88164147619 SUB ETHA DAT/SHV SUR/TCAS DEP/BARNARDS STAR 1215N35912W DEST/AS231042 DOF/120601 REG/TITANIC"+
						"EET/NZZO0029 38S162W0102 ENTRA3021200156 ENTRA0238 SEL/BPKS TYP/VOGON CONSTRUCTOR FLEET CODE/F10042 DLE/TAM0010 TAVEV0150150015 1712S12936E0018 OPR/TRANS STELLAR SPACE LINES ORGN/MEGADODO PER/H ALTN/ZZ9 PLURAL Z ALPHA"+
						"RALT/BRONTITALL TALT/EGLL URSA MINOR RIF/22N170W 1815N15330W KSFO RMK/DONT PANIC FROGSTAR IN PURSUIT"+
						"-E/2359 P/999 R/UVE S/PDMJ J/LFUV D/1 42 C GOLD A/BLACKER THAN BLACK N/TOWEL BABEL FISH C/TRILLIAN)";*/
	
    public static void main(String[] args){
		new AtsParse();
	}

	public AtsParse(){
		atsMsg.setAtsOtherInfo(new AtsOtherInformation());
		atsMsg.setAtsSuppInfo(new AtsSupplementaryInformation());
		
		parse();
		
		new AtsToFixm().convertAtsToFixm(atsMsg);
	}

	public AtsMessage parse(){
	
		String atsSections[] = rcvdMsg.split("-|–");
		
		for(String sec:atsSections){
			System.out.println(sec);
		}
		System.out.println("\n\n");

		setItem3To8(atsSections);
		setItem9(atsSections);
		setItem10(atsSections);
		setItem13(atsSections);
		setItem15(atsSections);
		setItem16(atsSections);
		setItem18(atsSections);
		setItem19(atsSections);
		setItem20(atsSections);
		
		return atsMsg;
	}

	private void setItem3To8(String[] items){
		setMsgType(items[0]);
		atsMsg.setIdentification(items[1]);
		System.out.println("Aircraft Identity:"+atsMsg.getIdentification());
		setRulesAndFlightType(items[2]);
	}

	/*1. Number of Aircraft 1-2 chars
	 * 2. Type Of Aircraft 2-4 Chars
	 * 3. Wake Turbulance 1 chars
	 * */
	private void setItem9(String[] atsSections) {
		
		String item = atsSections[3];
		
		List<MatchedGroup> groups = matchByGroups("(\\d{0,2})(\\S{2,4})(/(\\w))", item);
		
		/**Setting Aircraft Number**/
		atsMsg.setAircraftNum(groups.get(0).group);
		
		/**Setting Aircraft Type**/
		atsMsg.setAircraftTyp(groups.get(1).group);
		
		/**Setting Wake Turbulance Category**/
		atsMsg.setTurbulance(groups.get(3).group);
		
		System.out.println("Aircraft Num : "+atsMsg.getAircraftNum());
		System.out.println("Aircraft Type : "+atsMsg.getAircraftTyp());
		System.out.println("Turbulance : "+atsMsg.getTurbulance());
	}

	/*1. Set EquipmentCapabilities
	 * */
	private void setItem10(String[] atsSections) {
		String item = atsSections[4];
		String[] split10 = item.split("/");
		List<MatchedGroup> matchedGroups = matchByGroups("(^(N|S))(\\w+)", split10[0]);

		if(null != matchedGroups && matchedGroups.size()>0){
			atsMsg.setApproachAidEquipment(matchedGroups.get(0).group);
		}
		String serviceables = atsMsg.getApproachAidEquipment() == null ?  split10[0] : split10[0].substring(1, split10[0].length());
		List<MatchedGroup> servceItems = match("\\w\\d?", serviceables);
		System.out.println("Approach Aid : "+atsMsg.getApproachAidEquipment());
		System.out.println("Serviceables : ");
		for (MatchedGroup serviceItem : servceItems) {
			atsMsg.getServicableAidEquipment().add(serviceItem.group);
			System.out.println("\t"+ serviceItem.group);
		}
	}

	private void setItem13(String[] atsSections) {
		String item = atsSections[5];
		atsMsg.setDepAerodorme(item.substring(0, 4));
		atsMsg.setEstimatedOffBlockTime(item.substring(4, 8));
		System.out.println("Departure Aerodorme : "+atsMsg.getDepAerodorme());
		System.out.println("estimatedOffBlockTime : "+atsMsg.getEstimatedOffBlockTime());
	}
	
	private void setItem15(String[] atsSections) {
		String item = atsSections[6];
		List<MatchedGroup> groups = matchByGroups("(^((K\\d{4})|(N\\d{4})|(M\\d{3})))((F\\d{3})|(S\\d{4})|(A\\d{3})|(M\\d{4})|(VFR))\\s",item);
		if(null != groups && groups.size() > 0){
			atsMsg.setCruiseSpeed(groups.get(1).group);
			atsMsg.setCruiseLevel(groups.get(5).group);
		}
/*		atsMsg.setCruiseSpeed(item.substring(0, 4));  // TODO: Put regex matcher as its not predefined length
		atsMsg.setCruiseLevel(item.substring(4,8));  // TODO: Put regex matcher as its not predefined length
*/		
		System.out.println("Cruise Speed : "+atsMsg.getCruiseSpeed());
		System.out.println("Cruise Level : "+atsMsg.getCruiseLevel());
/*		List<MatchedGroup> routePoints = match("((\\s\\S+\\sVFR)|(DCT\\s\\S+((\\s\\S+[^((VFR)|(DCT))]))?\\s))", item.substring(8, item.length()));
		
		List<MatchedGroup> flightRuleRoute= match("\\s\\w+\\s((IFR)|(VFR))\\s?", item.substring(8, item.length()));
		
//		match("(DCT|IFR?((\\s(\\S+[^( \bDCT\b)]))+))++|(IFR?((\\s(\\S+[^( \bIFR\b)]))+))", item.substring(8, item.length()));
		System.out.println("Route : "+item.substring(8, item.length()));*/
		
		List<RouteSegment> routeSegments = routeSegmentation(item);
		System.out.println("Route Details ");
		System.out.println("\t"+routeSegments.toString());
	}
	
	private void setItem16(String[] atsSections) {
		String item = atsSections[7]; // Destination Aerodorme and EET
		atsMsg.setDestAerodorme(item.substring(0, 4));
		atsMsg.setDestEet(item.substring(4,8));
		
		System.out.println("Destination Aerodorme : "+atsMsg.getDestAerodorme());
		System.out.println("Destination EET : "+atsMsg.getDestEet());
		
		String items[] = item.split("\\s");
		
		System.out.println("Alternate Aerodormes : ");
		
		for(int i=1; i<items.length; i++){
			atsMsg.getAlternateAerodorme()[i-1] = items[i];
			System.out.println("\t"+atsMsg.getAlternateAerodorme()[i-1]);
		}
	}
	
	private void setItem18(String[] atsSections) {
		String item = atsSections[8]; // Destination Aerodorme and EET
		List<MatchedGroup> groups = match("\\w+/((\\w+\\s?)+[^(\\w+/)])", item);
		
		for (MatchedGroup gcaaMatcherIndex : groups) {
			setOtherInformation(gcaaMatcherIndex);
		}
		atsMsg.getAtsOtherInfo().print();
	}
	
	/**
	 * Altering Search And Rescue Information
	 * @param atsSections
	 */
	private void setItem19(String[] atsSections) {
		/*if(atsMsg.getMsgType().equals(anObject))*/
		if(atsSections.length <= 9 ) return;
		String item = atsSections[9]; // Supplementary Information
		List<MatchedGroup> groups = match("\\w+/((\\w+\\s?)+[^(\\w+/)])", item);
		
		for (MatchedGroup gcaaMatcherIndex : groups) {
			setSupplementaryInformation(gcaaMatcherIndex);
		}
		atsMsg.getAtsSuppInfo().print();
	}
	
	/**
	 * @param atsSections
	 */
	private void setItem20(String[] atsSections) {
		if(atsSections.length <= 10 ) return;
		String item = atsSections[10]; // Supplementary Information
		String[] groups = item.split("\\s");

		atsMsg.setOperatorId(groups[0]);
		atsMsg.setLastContactUnit(groups[1]);
		atsMsg.setLastTwoWayContact(groups[2]);
		atsMsg.setLastContactFrequency(groups[3]);
		atsMsg.setLastReportedPosition(groups[4]);
		atsMsg.setMethodLastKnownPosition(groups[5]);
		atsMsg.setActionByReportingUnit(groups[6]);
		atsMsg.setOtherPertinentInformation(groups[7]);

	}
	
	
	private void setMsgType(String section){
		String regex = "FPL|CHG|ARR|DLA|CNL|DEP|CPL|EST|CDN|ACP|LAM|RQP|RQS|SPL|ALR|RCF";
		List<MatchedGroup> msgIndexes= match(regex, section);
		
		if(msgIndexes != null  && msgIndexes.size()>0){
			atsMsg.setMsgType(msgIndexes.get(0).group);
		}
		System.out.println("MsgType:"+atsMsg.getMsgType());
	}
	
	private void setRulesAndFlightType(String section){ // One or Two characters
		if(section.length()==2){ // Single Rule
			atsMsg.setRules(section.substring(0,1));
			atsMsg.setFlightType(section.substring(1,2));

		}else if(section.length()==3){
			atsMsg.setRules(section.substring(0,2));
			atsMsg.setFlightType(section.substring(2,3));
		}

		System.out.println("Flight Rules:"+atsMsg.getRules());
		System.out.println("Flight Type:"+atsMsg.getFlightType());
	}

	
	private List<MatchedGroup> match(String regex, String section)
	{
    	Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(section);
        
        List<MatchedGroup> indexs = new ArrayList<MatchedGroup>();
        
        while(matcher.find()){
        	indexs.add(new MatchedGroup(matcher.start(), matcher.end(), matcher.group()));
/*        	System.out.println("Group is "+matcher.group());
        	System.out.println("Start is "+matcher.start());
        	System.out.println("End is "+matcher.end()+"\n\n");*/
        }
        return indexs;
	}

	private List<MatchedGroup> matchByGroups(String regex, String section)
	{
    	Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(section);
        
        List<MatchedGroup> indexs = new ArrayList<MatchedGroup>();
        
        if(matcher.find()){
        	for(int i=1;i<=matcher.groupCount();i++){
        		indexs.add(new MatchedGroup(matcher.start(i), matcher.end(i), matcher.group(i)));
        		//System.out.println(matcher.group(i));
        	}
        }
        return indexs;
	}
    
	private List<RouteSegment> routeSegmentation(String routeItem){

		String[] segments = routeItem.split("\\s");
		List<RouteSegment> routeSegments = new ArrayList<RouteSegment>();

		for (int i=1; i< segments.length ; i++) {
			RouteSegment segment = new RouteSegment();
			if(segments[i].equals("DCT")){
				segment.setAirway(segments[i]);
				routeSegments.add(segment);
				continue;
			}
			List<MatchedGroup> groups = match("^\\S{1,2}\\d{2,4}$",segments[i]);

			if(groups.size() == 1){
				segment.setAirway(groups.get(0).group);
				routeSegments.add(segment);
				continue;
			}
			groups=null;
			groups = matchByGroups("^((\\d{2,4}(N|S))(\\d{3,5}(E|W)))$",segments[i]);
			if(null != groups && groups.size()>0){
				segment.setNorthSouth(groups.get(1).group);
				segment.setEastWest(groups.get(3).group);
			}

			groups = null;
			groups = matchByGroups("^(\\S+\\d{6})$",segments[i]);
			if(null != groups && groups.size()>0){
				segment.setNorthSouth(groups.get(0).group);
/*				segment.setEastWest(groups.get(2).group);*/
				routeSegments.add(segment);
				continue;
			}

			if(segments[i].equalsIgnoreCase("VFR") || segments[i].equalsIgnoreCase("IFR")){
				RouteSegment prevSegment = routeSegments.get(routeSegments.size()-1);
				prevSegment.setChangeType(prevSegment.getChangeType() == null ? "":prevSegment.getChangeType());
				prevSegment.setChangeType(prevSegment.getChangeType().concat(" FLIGHT_RULES-"+ segments[i]+" "));
				continue;
			}
			
			groups = null;
			groups = matchByGroups("^(((\\S+\\d{6})|((\\d{2,4}(N|S))(\\d{3,5}(E|W))))/((((K\\d{4})|(N\\d{4})|(M\\d{3})))((F\\d{3})|(S\\d{4})|(A\\d{3})|(M\\d{4})|(VFR))))",segments[i]);
			if(null != groups && groups.size()>0){
				segment.setChangeType("Speed&Level Change");
				segment.setNorthSouth(groups.get(4).group);
				segment.setEastWest(groups.get(6).group);
				
				segment.setSpeed(groups.get(10).group);
				segment.setLevel(groups.get(15).group);
				routeSegments.add(segment);
				continue;
			}
			
			groups = null;
			groups = matchByGroups("^(((\\w+))/((((K\\d{4})|(N\\d{4})|(M\\d{3})))((F\\d{3})|(S\\d{4})|(A\\d{3})|(M\\d{4})|(VFR))))",segments[i]);
			if(null != groups && groups.size()>0){
				segment.setChangeType("Speed&Level Change");
				segment.setAtsJoiningPoint(groups.get(1).group);
				
				segment.setSpeed(groups.get(4).group);
				segment.setLevel(groups.get(9).group);
				routeSegments.add(segment);
				continue;
			}

			groups = null;
			groups = matchByGroups("C/(\\S+)/(\\S+)",segments[i]);
			if(null != groups && groups.size()>0){
				segment.setChangeType("Climb");
				segment.setAtsJoiningPoint(groups.get(0).group);
				
				segment.setSpeed(groups.get(1).group.substring(0,5));
				segment.setLevel(groups.get(1).group.substring(5,9));
				routeSegments.add(segment);
				continue;
			}
			
			segment.setFix(segments[i]);
			routeSegments.add(segment);
		}
		
		return routeSegments;
	}
	
/*	private void routeSegmentationMatcher(String routeItem){
		List<MatchedGroup> groups = match("\\S+", routeItem);
		
		for (int i=1; i< groups.size() ; i++) {
			RouteSegment segment = new RouteSegment();
			if(groups.get(i).group.equals("DCT")){
				segment.setAirway(groups.get(i).group);
				if(i == 1){					
					segment.setAtsJoiningPoint(groups.get(i+1).group);
					segment
				}
				
			}
		}
		
	}*/
	
	private void setOtherInformation(MatchedGroup group){
		String key = group.group.split("/")[0];
		String value = group.group.split("/")[1];
		
		AtsOtherInformation atsOther = atsMsg.getAtsOtherInfo();
		
		switch (key) {
		case "STS":
			atsOther.setSTS(value);
			break;
			
		case "STAR":
			atsOther.setStar(value);
			break;
			
		case "DOF":
			atsOther.setDOF(value);
			break;
			
		case "PBN":
			atsOther.setPBN(value);
			break;
			
		case "RMK":
			atsOther.setRMK(value);
			break;
			
		case "REG":
			atsOther.setREG(value);
			break;
			
		case "EET":
			atsOther.setEET(value);
			break;
			
		case "CODE":
			atsOther.setCODE(value);
			break;
			
		case "SEL":
			atsOther.setSEL(value);
			break;
			
		case "OPR":
			atsOther.setOPR(value);
			break;
			
		case "NAV":
			atsOther.setNAV(value);
			break;
			
		case "COM":
			atsOther.setCOM(value);
			break;
			
		case "DAT":
			atsOther.setDAT(value);
			break;
			
		case "SUR":
			atsOther.setSUR(value);
			break;
			
		case "DEP":
			atsOther.setDEP(value);
			break;
			
		case "DEST":
			atsOther.setDEST(value);
			break;
			
		case "TYP":
			atsOther.setTYP(value);
			break;
			
		case "DLE":
			atsOther.setDLE(value);
			break;
			
		case "ORGN":
			atsOther.setORGN(value);
			break;
			
		case "PER":
			atsOther.setPER(value);
			break;
			
		case "ALTN":
			atsOther.setALTN(value);
			break;
			
		case "RALT":
			atsOther.setRALT(value);
			break;
			
		case "TALT":
			atsOther.setTALT(value);
			break;
		case "RIF":
			atsOther.setRIF(value);
			break;
			
			
		default:
			break;
		}
	}
	
	private void setSupplementaryInformation(MatchedGroup group){
		String key = group.group.split("/")[0];
		String value = group.group.split("/")[1];
		
		AtsSupplementaryInformation atsSupp = atsMsg.getAtsSuppInfo();
		
		switch (key) {
		case "E":
			atsSupp.setFuelEndurance(value);
			break;
			
		case "P":
			atsSupp.setPersonsOnBoard(value);
			break;
			
		case "R":
			atsSupp.setFrequencyELT(value);
			break;
			
		case "S":
			atsSupp.setSurvivalEquipment(value);
			break;
			
		case "J":
			atsSupp.setLifeJacket(value);
			break;
			
		case "D":
			atsSupp.setDinghiesInfo(value);
			break;
			
		case "A":
			atsSupp.setAircraftColor(value);
			break;
			
		case "N":
			atsSupp.setPlainLanguage(value);
			break;
			
		case "C":
			atsSupp.setPilotInCommand(value);
			break;
			
		default:
			break;
		}
	}
	
}