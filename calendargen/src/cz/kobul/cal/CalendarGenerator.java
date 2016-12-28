package cz.kobul.cal;

import java.util.Calendar;

// generovani LaTeX kalendare
public class CalendarGenerator {


	public static final String[] MONTHS = new String[] {"\\Jan","\\Feb","\\Mar","\\Apr","\\May","\\Jun",
	          "\\Jul","\\Aug","\\Sep","\\Oct","\\Nov","\\Dec"};
	public static final String[] DOWEEK = new String[] {"", "\\Su","\\Mo","\\Tu","\\We","\\Th","\\Fr","\\Sa"};
	
	public static void main(String[] args) {
		if (args.length == 0 || args.length > 1) {
			System.out.println("Parametr: rok");
			return;
		}
		int year = 2000;
		try {
			year = Integer.parseInt(args[0]);
		}
		catch (NumberFormatException ex) {
			System.out.println("Neplatny parametr: '" + args[0] + "'");
			System.out.println("Parametr: rok");
			return;			
		}
		
		StringBuilder result = new StringBuilder(1000);
		
		Calendar calendar = Calendar.getInstance();		
		for (int i = 0; i < 12; i++) {
			result.append(" \\Month{").append(i < 9 ? "0" : "").append(i + 1);
			result.append("}{").append(i + 1).append("}{").append(MONTHS[i]).append("}{\n");
			result.append("  \\begin{tabular}[t]{");

			StringBuilder inner1 = new StringBuilder(100);
			StringBuilder inner2 = new StringBuilder(100);
			calendar.set(year, i, 1);
			while (calendar.get(Calendar.MONTH) == i) {
				result.append('c');
				int dow = calendar.get(Calendar.DAY_OF_WEEK);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				if (day > 1) {
					inner1.append(" & ");
					inner2.append(" & ");
				}
				
				inner1.append(DOWEEK[dow]);
				inner2.append(dow == Calendar.SUNDAY ? "\\Dsr " : "\\Ds ").append(day);
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
			result.append("}\n");
			result.append("    ").append(inner1).append("\\\\ \n");
			result.append("    ").append(inner2).append("\\\\ \n");
			result.append("  \\end{tabular}\n");
			result.append(" }\n");			
		}
		
		System.out.println(result);
		
	}
	
}
