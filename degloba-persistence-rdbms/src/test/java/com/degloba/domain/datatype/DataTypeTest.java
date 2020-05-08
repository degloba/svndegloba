package com.degloba.domain.datatype;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import com.degloba.domain.DataType;

import static org.junit.jupiter.api.Assertions.*;

public class DataTypeTest {

    private final String[] DATE_FORMAT = new String[]{
        "yyyy-MM-dd",
        "hh:mm:ss",
        "yyyy-MM-dd hh:mm:ss"
    };

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetValue() throws ParseException {
        assertEquals("abc", DataType.STRING.getValue("abc"));
        assertEquals(12, DataType.INT.getValue("12"));
        assertEquals(12L, DataType.LONG.getValue("12"));
        assertEquals(12.5, (Double) DataType.DOUBLE.getValue("12.5"), 0.001);
        assertEquals(BigDecimal.valueOf(12.5), DataType.BIG_DECIMAL.getValue("12.5"));
        assertEquals(true, DataType.BOOLEAN.getValue("true"));
        assertEquals(false, DataType.BOOLEAN.getValue("false"));
        assertEquals(DateUtils.parseDate("2000-01-01", DATE_FORMAT), DataType.DATE.getValue("2000-01-01"));
        assertEquals(DateUtils.parseDate("00:12:00", DATE_FORMAT), DataType.TIME.getValue("00:12:00"));
        assertEquals(DateUtils.parseDate("2000-01-01 00:12:00", DATE_FORMAT), DataType.DATE_TIME.getValue("2000-01-01 00:12:00"));
    }

    @Test
    public void testGetDefaultValue() throws ParseException {
        assertEquals("", DataType.STRING.getValue(null));
        assertEquals(0, DataType.INT.getValue(""));
        assertEquals(0L, DataType.LONG.getValue(null));
        assertEquals(0.0, (Double) DataType.DOUBLE.getValue(""), 0.001);
        assertEquals(BigDecimal.ZERO, DataType.BIG_DECIMAL.getValue(null));
        assertEquals(false, DataType.BOOLEAN.getValue(null));
        assertNull(DataType.DATE.getValue(null));
        assertNull(DataType.TIME.getValue(""));
        assertNull(DataType.DATE_TIME.getValue(" "));
    }
    
/* Només Junit 4   
 * @Test(expected = IllegalArgumentException.class)
    public void testDateException() {
        DataType.DATE.getValue("xxx-02-32");
    }*/
    
    public void testDateException() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
        	DataType.DATE.getValue("xxx-02-32");
        });
    }
    
/* Només Junit 4   
 * @Test(expected = IllegalArgumentException.class)
    public void testTimeException() {
        DataType.DATE.getValue("28:70:88");
    }*/
    
    public void testTimeException() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
        	DataType.DATE.getValue("28:70:88");
        });
    }
    
/* Només Junit 4   
 * @Test(expected = IllegalArgumentException.class)
    public void testDateTimeException() {
        DataType.DATE.getValue("xxy-02-30 00:34:11");
    }*/
    public void testDateTimeException() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
        	DataType.DATE.getValue("xxy-02-30 00:34:11");
        });
    }
}
