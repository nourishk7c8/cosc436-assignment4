
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MenuTest {
	private Menu m;

	@Before
	public void setUp() throws Exception {
		m = new Menu();
	}

	@After
	public void tearDown() throws Exception {
		m = null;
	}

	@Test
	public void testGetFirstItem() {
		assertNull(m.getFirstItem());
	}

	@Test
	public void testSetFirstItem() {
		MenuItem item = new MenuItem("Ghgjh", Menu.APPETIZERS, Menu.NOT_HEART_HEALTHY, 3.45);
		m.setFirstItem(item);
		assertEquals(item, m.getFirstItem());//fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		MenuItem item = new MenuItem("Ghgjh", Menu.APPETIZERS, Menu.NOT_HEART_HEALTHY, 3.45);
		m.add(item);
		assertEquals(item, m.getFirstItem());//fail("Not yet implemented");
	}

}
