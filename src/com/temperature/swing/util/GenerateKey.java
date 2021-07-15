package com.temperature.swing.util;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/30 22:09
 */
public class GenerateKey
{
	public static int[] pList;

	static
	{
		pList = new int[10];
		pList[0]=19210;
		pList[1]=54644;
		pList[2]=73432;
		pList[3]=43322;
		pList[4]=23435;
		pList[5]=66874;
		pList[6]=12354;
		pList[7]=87543;
		pList[8]=65321;
		pList[9]=98654;
	}

	public static int keyIncrease(int key, int h,int id)
	{
		return key + id * pList[h];
	}

	public static int generateKey(int i, int j, int k)
	{
		return i * pList[0] + j * pList[1] + k * pList[2];
	}
	public static int generateKey(int i, int j)
	{
		return i * pList[0] + j * pList[1] ;
	}

	public static int generateKey(int p1, int p2, int p3, int p4)
	{
		return p1 * pList[0] + p2 * pList[1] + p3 * pList[2] + p4 * pList[3];
	}
}
