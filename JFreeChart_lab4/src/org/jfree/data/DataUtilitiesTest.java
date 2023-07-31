package org.jfree.data;
import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



public class DataUtilitiesTest extends DataUtilities {

    private Mockery mockingContext;
    private Values2D values;
    private DefaultKeyedValues kv;

    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }
    
    @Before
    public void setUp() throws Exception {
        this.mockingContext = new Mockery();
        this.values = this.mockingContext.mock(Values2D.class);
        this.kv = new DefaultKeyedValues();
    }
    
    
    // Tests for calculateColumnTotal(Values2D, int)
    @Test
    public void testCalculateColumnTotal_AllPositiveValues_FirstColumn() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(10.0));
            one(values).getValue(1, 0); will(returnValue(20.0));
            one(values).getValue(2, 0); will(returnValue(30.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(60.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_AllPositiveValues_MiddleColumn() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 1); will(returnValue(15.0));
            one(values).getValue(1, 1); will(returnValue(25.0));
            one(values).getValue(2, 1); will(returnValue(35.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 1);
        assertEquals(75.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_AllPositiveValues_LastColumn() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 2); will(returnValue(20.0));
            one(values).getValue(1, 2); will(returnValue(30.0));
            one(values).getValue(2, 2); will(returnValue(40.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 2);
        assertEquals(90.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_MaxDoubleValue_FirstColumn() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(Double.MAX_VALUE));
            one(values).getValue(1, 0); will(returnValue(50.0));
            one(values).getValue(2, 0); will(returnValue(-50.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(Double.MAX_VALUE, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_MinDoubleValue_FirstColumn() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(Double.MIN_VALUE));
            one(values).getValue(1, 0); will(returnValue(50.0));
            one(values).getValue(2, 0); will(returnValue(-50.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(Double.MIN_VALUE, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_MaxIntegerValue_ColumnIndex() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, Integer.MAX_VALUE); will(returnValue(10.0));
            one(values).getValue(1, Integer.MAX_VALUE); will(returnValue(20.0));
            one(values).getValue(2, Integer.MAX_VALUE); will(returnValue(30.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values, Integer.MAX_VALUE);
        assertEquals(60.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_MinIntegerValue_ColumnIndex() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, Integer.MIN_VALUE); will(returnValue(10.0));
            one(values).getValue(1, Integer.MIN_VALUE); will(returnValue(20.0));
            one(values).getValue(2, Integer.MIN_VALUE); will(returnValue(30.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values, Integer.MIN_VALUE);
        assertEquals(60.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_SumZero_FirstColumn() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(10.0));
            one(values).getValue(1, 0); will(returnValue(20.0));
            one(values).getValue(2, 0); will(returnValue(-30.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_NullValues2D() {
    	try {
	        double result = DataUtilities.calculateColumnTotal(null, 0);
	        assertEquals(0, result, .000000001d);
    	} catch (NullPointerException e) {
    		// If we get an error then this test case has failed
    		fail("NullPointerException was thrown!");
    	} catch(IllegalArgumentException e) {
    		
    	}
    }

    @Test
    public void testCalculateColumnTotal_NegativeColumnIndex() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, -1); will(returnValue(10.0));
            one(values).getValue(1, -1); will(returnValue(20.0));
            one(values).getValue(2, -1); will(returnValue(30.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values, -1);
        assertEquals(60.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_EmptyValues2D() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(0));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_Values2DWithNaN() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(Double.NaN));
            one(values).getValue(1, 0); will(returnValue(50.0));
            one(values).getValue(2, 0); will(returnValue(100.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(Double.NaN, result, .000000001d);
    }
    
    
    // Tests for calculateColumnTotal(Values2D, int, int[])
    @Test
    public void testCalculateColumnTotal_AllPositiveValuesInFirstColumnWithFirstAndLastValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(10.0));
            one(values).getValue(1, 0); will(returnValue(20.0));
            one(values).getValue(2, 0); will(returnValue(30.0));
        }});
        
        int[] validCols = {0,2};

        double result = DataUtilities.calculateColumnTotal(values, 0, validCols);
        assertEquals(40.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_AllPositiveValuesInMiddleColumnWithFirstAndLastValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 1); will(returnValue(15.0));
            one(values).getValue(1, 1); will(returnValue(25.0));
            one(values).getValue(2, 1); will(returnValue(35.0));
        }});
        
        int[] validCols = {0,2};
        double result = DataUtilities.calculateColumnTotal(values, 1, validCols);
        assertEquals(50.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_AllPositiveValuesInLastColumnWithFirstAndSecondValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 2); will(returnValue(20.0));
            one(values).getValue(1, 2); will(returnValue(30.0));
            one(values).getValue(2, 2); will(returnValue(40.0));
        }});
        int[] validCols = {0,1};
        double result = DataUtilities.calculateColumnTotal(values, 2, validCols);
        assertEquals(50.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_MaxDoubleValueInFirstColumnWithFirstAndSecondValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(Double.MAX_VALUE));
            one(values).getValue(1, 0); will(returnValue(50.0));
            one(values).getValue(2, 0); will(returnValue(-50.0));
        }});
        int[] validCols = {0,1};
        double result = DataUtilities.calculateColumnTotal(values, 0, validCols);
        assertEquals((Double.MAX_VALUE+50.0), result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_MinDoubleValueInFirstColumnWithFirstAndSecondValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(Double.MIN_VALUE));
            one(values).getValue(1, 0); will(returnValue(50.0));
            one(values).getValue(2, 0); will(returnValue(-50.0));
        }});
        int[] validCols = {0,1};
        double result = DataUtilities.calculateColumnTotal(values, 0, validCols);
        assertEquals((Double.MIN_VALUE+50.0), result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_MaxIntegerValue_ColumnIndexWithSecondAndThirdValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, Integer.MAX_VALUE); will(returnValue(10.0));
            one(values).getValue(1, Integer.MAX_VALUE); will(returnValue(20.0));
            one(values).getValue(2, Integer.MAX_VALUE); will(returnValue(30.0));
        }});
        int[] validCols = {1,2};
        double result = DataUtilities.calculateColumnTotal(values, Integer.MAX_VALUE, validCols);
        assertEquals(50.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_MinIntegerValue_ColumnIndexWithSecondAndThirdValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, Integer.MIN_VALUE); will(returnValue(10.0));
            one(values).getValue(1, Integer.MIN_VALUE); will(returnValue(20.0));
            one(values).getValue(2, Integer.MIN_VALUE); will(returnValue(30.0));
        }});
        int[] validCols = {1,2};
        double result = DataUtilities.calculateColumnTotal(values, Integer.MIN_VALUE, validCols);
        assertEquals(50.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_SumZero_FirstColumnWithAllValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(10.0));
            one(values).getValue(1, 0); will(returnValue(20.0));
            one(values).getValue(2, 0); will(returnValue(-30.0));
        }});
        int[] validCols = {0,1,2};
        double result = DataUtilities.calculateColumnTotal(values, 0, validCols);
        assertEquals(0, result, .000000001d);
    }
    
    @Test
    public void testCalculateColumnTotal_SumZero_FirstColumnWithZeroValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(10.0));
            one(values).getValue(1, 0); will(returnValue(20.0));
            one(values).getValue(2, 0); will(returnValue(-30.0));
        }});
        int[] validCols = {};
        double result = DataUtilities.calculateColumnTotal(values, 0, validCols);
        assertEquals(0.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_NullValues2DWithZeroValidCols() {
    	try {
    		int[] validCols = {};
	        double result = DataUtilities.calculateColumnTotal(null, 0, validCols);
	        assertEquals(0, result, .000000001d);
    	} catch (NullPointerException e) {
    		// If we get an error then this test case has failed
    		fail("NullPointerException was thrown!");
    	} catch(IllegalArgumentException e) {
    		
    	}
    }

    @Test
    public void testCalculateColumnTotal_NegativeColumnIndexWithOneValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, -1); will(returnValue(10.0));
            one(values).getValue(1, -1); will(returnValue(20.0));
            one(values).getValue(2, -1); will(returnValue(30.0));
        }});
        int[] validCols = {1};
        double result = DataUtilities.calculateColumnTotal(values, -1, validCols);
        assertEquals(20.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_EmptyValues2DWithOneValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(0));
        }});
        int[] validCols = {1};
        double result = DataUtilities.calculateColumnTotal(values, 0, validCols);
        assertEquals(0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotal_Values2DWithNaNWithOneValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(Double.NaN));
            one(values).getValue(1, 0); will(returnValue(50.0));
            one(values).getValue(2, 0); will(returnValue(100.0));
        }});
        int[] validCols = {0};
        double result = DataUtilities.calculateColumnTotal(values, 0, validCols);
        assertEquals(Double.NaN, result, .000000001d);
    }
    
    
    
    // Tests for getCumulativePercentages(KeyedValues data)
    @Test
    public void testGetCumulativePercentages_EmptyKeyedValues() {
        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertEquals(0, result.getItemCount());
    }

    @Test
    public void testGetCumulativePercentages_SingleRowKeyedValue() {
        kv.addValue((Comparable<?>) 0, 5.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertEquals(1.0, result.getValue(0).doubleValue(), 0.000000001d);
    }

    @Test
    public void testGetCumulativePercentages_SingleRowKeyedValueZeroValue() {
        kv.addValue((Comparable<?>) 0, 0.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertEquals(Double.NaN, result.getValue(0).doubleValue(), 0.000000001d);
    }

    @Test
    public void testGetCumulativePercentages_SingleRowKeyedValueNullValue() {
        kv.addValue((Comparable<?>) 0, null);
        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertEquals(Double.NaN, result.getValue(0).doubleValue(), 0.000000001d);
    }

    @Test
    public void testGetCumulativePercentages_MixedValuesIncludingNull() {
        kv.addValue((Comparable<?>) 0, -3.5);
        kv.addValue((Comparable<?>) 1, 4.0);
        kv.addValue((Comparable<?>) 2, null);
        kv.addValue((Comparable<?>) 3, 10.5);
        kv.addValue((Comparable<?>) 4, 0.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertEquals((0.5/11.0), result.getValue(1).doubleValue(), 0.000000001d);
    }

    @Test
    public void testGetCumulativePercentages_AllPositiveValues() {
        kv.addValue((Comparable<?>) 0, 5);
        kv.addValue((Comparable<?>) 1, 9);
        kv.addValue((Comparable<?>) 2, 2);
        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        Map<Comparable, Double> expectedResults = new HashMap<>();
        expectedResults.put(0, 0.3125);
        expectedResults.put(1, 0.875);
        expectedResults.put(2, 1.0);
        expectedResults.keySet().forEach(key -> assertEquals(expectedResults.get(key), result.getValue(key).doubleValue(), 0.000000001d));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCumulativePercentages_NullKeyedValues() {
        kv = null;
        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
    }

    @Test
    public void testGetCumulativePercentages_ZeroValues() {
        kv.addValue((Comparable<?>) 0, 0.0);
        kv.addValue((Comparable<?>) 1, 0.0);
        kv.addValue((Comparable<?>) 2, 0.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertEquals(Double.NaN, result.getValue(2).doubleValue(), 0.000000001d);
    }


    // tests for createNumberArray2D
    @Test
    public void testCreateNumberArray2DAllNegativeValues() {
    	double[][] doubleArray = { { -0.5, -0.5 }, { -0.5, -0.5 } };
        Number[][] expectedArray = { { -0.5, -0.5 }, { -0.5, -0.5 } };
        
        assertArrayEquals("The expected array should be an exact copy of all the negative values", 
        		expectedArray, DataUtilities.createNumberArray2D(doubleArray));
    }

    @Test
    public void testCreateNumberArray2DAllPositiveValues() {
    	double[][] doubleArray = { { 0.5, 0.5 }, { 0.5, 0.5 } };
        Number[][] expectedArray = { { 0.5, 0.5 }, { 0.5, 0.5 } };
        
        assertArrayEquals("The expected array should be an exact copy of all the positive values", 
        		expectedArray, DataUtilities.createNumberArray2D(doubleArray));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreateNumberArray2DwithNull() {
    	DataUtilities.createNumberArray2D(null);
    	}
    
    @Test
    public void testCreateNumberArray2DLargeArray() {
    	double[][] doubleArray = { { 0.5, -0.5, -0.5, 0.5 }, { -0.5, 0.5, -0.5, 0.5 }, { 0.5, 0.5, -0.5, 0.5 }, { 0.5, 0.5, -0.5, -0.5 } };
        Number[][] expectedArray = { { 0.5, -0.5, -0.5, 0.5 }, { -0.5, 0.5, -0.5, 0.5 }, { 0.5, 0.5, -0.5, 0.5 }, { 0.5, 0.5, -0.5, -0.5 } };
        
        assertArrayEquals("The expected array should be an exact copy of a large array", 
        		expectedArray, DataUtilities.createNumberArray2D(doubleArray));
    }
    
    @Test
    public void testCreateNumberArray2DSmallArray() {
    	double[][] doubleArray = { { -0.5 }, { 0.5 } };
        Number[][] expectedArray = { { -0.5 }, { 0.5 } };
        
        assertArrayEquals("The expected array should be an exact copy of a small array values", 
        		expectedArray, DataUtilities.createNumberArray2D(doubleArray));
    }
    
    @Test
    public void testCreateNumberArray2DEmpty() {
    	double[][] doubleArray = {};
        Number[][] expectedArray = {};
        
        assertArrayEquals("The expected array should be an exact copy of a null array", 
        		expectedArray, DataUtilities.createNumberArray2D(doubleArray));
    }
    
    @Test
    public void testCreateNumberArray2DBLB() {
    	double[][] doubleArray = { { -1.1, -1.1 }, { -1.1, -1.1 } };
        Number[][] expectedArray = { { -1.1, -1.1 }, { -1.1, -1.1 } };
        
        assertArrayEquals("The expected array should be an exact copy with BLB values", 
        		expectedArray, DataUtilities.createNumberArray2D(doubleArray));
    }
    
    @Test
    public void testCreateNumberArray2DALB() {
    	double[][] doubleArray = { { -0.9, -0.9 }, { -0.9, -0.9 } };
        Number[][] expectedArray = { { -0.9, -0.9 }, { -0.9, -0.9 } };
        
        assertArrayEquals("The expected array should be an exact copy with ALB values", 
        		expectedArray, DataUtilities.createNumberArray2D(doubleArray));
    }
    
    @Test
    public void testCreateNumberArray2DBUB() {
    	double[][] doubleArray = { { 0.9, 0.9 }, { 0.9, 0.9 } };
        Number[][] expectedArray = { { 0.9, 0.9 }, { 0.9, 0.9 } };
        
        assertArrayEquals("The expected array should be an exact copy with BUB values", 
        		expectedArray, DataUtilities.createNumberArray2D(doubleArray));
    }
    
    @Test
    public void testCreateNumberArray2DAUB() {
    	double[][] doubleArray = { { 1.1, 1.1 }, { 1.1, 1.1 } };
        Number[][] expectedArray = { { 1.1, 1.1 }, { 1.1, 1.1 } };
        
        assertArrayEquals("The expected array should be an exact copy with AUB values", 
        		expectedArray, DataUtilities.createNumberArray2D(doubleArray));
    }
    
    @Test
    public void testCreateNumberArray2DUB() {
    	double[][] doubleArray = { { 1.0, 1.0 }, { 1.0, 1.0 } };
        Number[][] expectedArray = { { 1.0, 1.0 }, { 1.0, 1.0 } };
        
        assertArrayEquals("The expected array should be an exact copy with UB values", 
        		expectedArray, DataUtilities.createNumberArray2D(doubleArray));
    }
    
    @Test
    public void testCreateNumberArray2DLB() {
    	double[][] doubleArray = { { -1.0, -1.0 }, { -1.0, -1.0 } };
        Number[][] expectedArray = { { -1.0, -1.0 }, { -1.0, -1.0 } };
        
        assertArrayEquals("The expected array should be an exact copy with LB values", 
        		expectedArray, DataUtilities.createNumberArray2D(doubleArray));
    }

	 //Test createNumberArray method
	@Test
	public void createNumberArrayPositive() {
		double[] testInput = {1.0, 2.2,3.7};
		Number[] testOutput = {1.0, 2.2,3.7};
		
		Number[] result = DataUtilities.createNumberArray(testInput);
		
		assertArrayEquals(testOutput, result);
	}
	@Test
	public void createNumberArrayNegative() {
		double[] testInput = {-1.0,-2.2,-3.7};
		Number[] testOutput = {-1.0,-2.2,-3.7};
		
		Number[] result = DataUtilities.createNumberArray(testInput);
		
		assertArrayEquals(testOutput, result);
	}
	
	@Test
	public void createNumberArrayNullInput() {
		try {
			double[] testInput = null;
			DataUtilities.createNumberArray(testInput);
			fail("This method should throw an exception!");
			// exception
		} catch (Exception e) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					e.getClass());
			// catching the exception, asserting that an IllegalArgumentException was thrown
		}
	}
	@Test
	public void createNumberArrayZero() {
		double[] testInput = {0.0,0.0};
		Number[] testOutput = {0.0,0.0};
		
		Number[] result = DataUtilities.createNumberArray(testInput);
		
		assertArrayEquals(testOutput, result);
	}
	@Test
	public void createNumberArrayEmpty() {
		double[] testInput = {};
		Number[] testOutput = {};
		
		Number[] result = DataUtilities.createNumberArray(testInput);
		
		assertArrayEquals(testOutput, result);
	}
	@Test
	public void createNumberArraySingle() {
		double[] testInput = {1.0};
		Number[] testOutput = {1.0};
		
		Number[] result = DataUtilities.createNumberArray(testInput);
		
		assertArrayEquals(testOutput, result);
	}

	@Test
	public void createNumberArrayMaxThree() {
		double[] testInput = {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
		Number[] testOutput = {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
		
		Number[] result = DataUtilities.createNumberArray(testInput);
		
		assertArrayEquals(testOutput, result);
	}
	@Test
	public void createNumberArrayMinThree() {
		double[] testInput = { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE};
		Number[] testOutput = { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE};
		
		Number[] result = DataUtilities.createNumberArray(testInput);
		
		assertArrayEquals(testOutput, result);
	}
	//Test calculaterowtotal method
	@Test
    public void calculateRowTotalPositiveFirstRow() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(10.0));
            one(values).getValue(0, 1); will(returnValue(20.0));
            one(values).getValue(0, 2); will(returnValue(30.0));
        }});

        double result = DataUtilities.calculateRowTotal(values,0);
        assertEquals(60.0, result, .000000001d);
    }
	@Test
	public void calculateRowTotalNegativeFirstRow() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(-10.0));
            one(values).getValue(0, 1); will(returnValue(-20.0));
            one(values).getValue(0, 2); will(returnValue(-30.0));
        }});

        double result = DataUtilities.calculateRowTotal(values,0);
        assertEquals(-60.0, result, .000000001d);
    }
	@Test
    public void calculateRowTotalPositiveSecondRow() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(1, 0); will(returnValue(10.0));
            one(values).getValue(1, 1); will(returnValue(20.0));
            one(values).getValue(1, 2); will(returnValue(30.0));
        }});

        double result = DataUtilities.calculateRowTotal(values,1);
        assertEquals(60.0, result, .000000001d);
    }
	@Test
    public void calculateRowTotalPositiveThirdRow() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(2, 0); will(returnValue(10.0));
            one(values).getValue(2, 1); will(returnValue(20.0));
            one(values).getValue(2, 2); will(returnValue(30.0));
        }});

        double result = DataUtilities.calculateRowTotal(values,2);
        assertEquals(60.0, result, .000000001d);
    }
	@Test
	public void calculateRowTotalMaxValueFirstRow() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(Double.MAX_VALUE));
            one(values).getValue(0, 1); will(returnValue(50.0));
            one(values).getValue(0, 2); will(returnValue(-50.0));
        }});

        double result = DataUtilities.calculateRowTotal(values,0);
        assertEquals(Double.MAX_VALUE, result, .000000001d);
    }
	@Test
	public void calculateRowTotalNanFirstRow() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(Double.NaN));
            one(values).getValue(0, 1); will(returnValue(50.0));
            one(values).getValue(0, 2); will(returnValue(-50.0));
        }});

        double result = DataUtilities.calculateRowTotal(values,0);
        assertEquals(Double.NaN, result, .000000001d);
    }
	@Test
	public void calculateRowTotalMinValueFirstRow() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(Double.MIN_VALUE));
            one(values).getValue(0, 1); will(returnValue(50.0));
            one(values).getValue(0, 2); will(returnValue(-50.0));
        }});

        double result = DataUtilities.calculateRowTotal(values,0);
        assertEquals(Double.MIN_VALUE, result, .00000001d);
    }
	
	@Test
	public void calculateRowTotalMaxIndexFirstRow() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(Integer.MAX_VALUE, 0); will(returnValue(10.0));
            one(values).getValue(Integer.MAX_VALUE, 1); will(returnValue(20.0));
            one(values).getValue(Integer.MAX_VALUE, 2); will(returnValue(30.0));
        }});

        double result = DataUtilities.calculateRowTotal(values,Integer.MAX_VALUE);
        assertEquals(60.0, result, .000000001d);
    }
	@Test
	public void calculateRowTotalMinIndexFirstRow() {
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(Integer.MIN_VALUE, 0); will(returnValue(10.0));
            one(values).getValue(Integer.MIN_VALUE, 1); will(returnValue(20.0));
            one(values).getValue(Integer.MIN_VALUE, 2); will(returnValue(30.0));
        }});

        double result = DataUtilities.calculateRowTotal(values,Integer.MIN_VALUE);
        assertEquals(60.0, result, .000000001d);
    }
	@Test
	public void calculateRowTotalEmpty() {
		  mockingContext.checking(new Expectations() {{
	            one(values).getColumnCount(); will(returnValue(0));
	        }});

	        double result = DataUtilities.calculateRowTotal(values,0);
	        assertEquals(0, result, .000000001d);
	    
	}
	@Test
    public void calculateRowTotal_NullValue() {
    	try {
	        double result = DataUtilities.calculateRowTotal(null, 0);
	        assertEquals(0, result, .000000001d);
    	} catch (NullPointerException e) {
    		// If we get an error then this test case has failed
    		fail("NullPointerException was thrown!");
    	} catch (IllegalArgumentException e) {
    		
    	}
    }
    
    

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}