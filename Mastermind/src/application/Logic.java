package application;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Logic 
{
	public static ArrayList<String> generateSecretCode(ArrayList<String>colors)
	{
		ArrayList<String> back = new ArrayList<String>();
		Random rnd = new Random();
		int min = 0;
		int max = 5;
		
		for(int a = 0; a < colors.size();a++)
		{
			int colorNumber = 0;
			colorNumber = rnd.nextInt(max - min) + min;
			back.add(colors.get(colorNumber));
		}
		
		return back;
	}
	
	public static ArrayList<String> checkCode(ArrayList<String> input, ArrayList<String> solution, ArrayList<String> colors)
	{
		ArrayList<String> back = new ArrayList<String>();
		
		int whiteTotal = 0;
		int blackTotal = 0;
		
		for(int a = 0; a < colors.size(); a++)
		{
			String tempColor = "";
			
			if(include(solution,colors.get(a)))
			{
				tempColor = colors.get(a);
				
				for(int b = 0; b < solution.size(); b++)
				{
					if(solution.get(b).equals(input.get(b)))
					{
						blackTotal++;
					}
					
					whiteTotal += getWhite(input,b);
				}
			}
		}
		
		return back;
	}
	
	private static int getWhite(ArrayList<String> input,int actualPos)
	{
		int back = 0;
		String color = input.get(actualPos);
		
		for(int a = 0; a < input.size(); a++)
		{
			if(a != actualPos)
			{
				if(input.get(a).equals(color))
				{
					back++;
				}
			}
		}
		
		return back;
	}
	
	private static boolean include(ArrayList<String> input, String toMatch)
	{
		boolean back = false;
		
		for(String s: input)
		{
			if(s.equals(toMatch))
			{
				back = true;
				break;
			}
		}
		
		return back;
	}
}
