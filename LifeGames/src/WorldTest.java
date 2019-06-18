import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WorldTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testChangeSpeedFast() {
		//错误测试
		World.speed = 4;
		World.speed = 2;
		//World.speed = 1;
		assertEquals(1,World.speed);
	}
	World wor;
	@Test
	public void testSetShape() {
		int [][]shape = new int[40][50];
		//一般测试
		shape[10][10]=1;
		shape[11][11]=1;
		shape[10][11]=1;
		shape[11][10]=1;
		World.isChanging=true;
		int arrowsRows=shape.length;
		int arrowsColumns=shape[0].length;
		World.CellStatus currentGeneration[][] = new World.CellStatus[arrowsRows][arrowsColumns];
		boolean flag = true;
		synchronized(this)
		{
			for(int i=0;i<arrowsRows;i++)
			{
				for(int j=0;j<arrowsColumns;j++)
				{
					currentGeneration[i][j]=World.CellStatus.Dead;
				}
			}
			for(int i=0;i<arrowsRows;i++)
			{
				for(int j=0;j<arrowsColumns;j++)
				{
					if(shape[i][j]==1)
					{
						currentGeneration[i][j]=World.CellStatus.Active;
					}
				}
			}
			if(currentGeneration[10][10]!=World.CellStatus.Active||currentGeneration[11][11]!=World.CellStatus.Active||currentGeneration[11][10]!=World.CellStatus.Active||currentGeneration[10][11]!=World.CellStatus.Active)
				flag = false;
			World.isChanging=false;
			this.notifyAll();
		}
		assertEquals(true, flag);
	}

	@Test
	public void testSetZero() {
		boolean flag = true;
		for(int i=0;i<World.rows;i++){
			for(int j=0;i<World.columns;j++)
				World.zero[i][j]=0;
		}
		for(int i=0;i<World.rows;i++)
		{
			for(int j=0;j<World.columns;j++)
			{
				if(World.zero[i][j]!=0)
					flag = false;break;
			}
		}
		assertEquals(true, flag);
	}


}
