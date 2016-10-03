package com.degloba.domain.persistence.nosql.googleDatastore.api.jpa;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.time.DateUtils;

import com.degloba.domain.DataType;
import com.degloba.domain.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 * Value. The value of this type is mainly used for custom attributes Entity class, for example, the Employee class to dynamically add a number of attributes, each type of data is recorded and a string
 */
@Embeddable
public class Value implements ValueObject {

    private static final long serialVersionUID = 4254026874177282302L;

    private static final String[] DATE_TIME_FORMAT = {
        "yyyy-MM-dd",
        "hh:mm",
        "hh:mm:ss",
        "yyyy-MM-dd hh:mm",
        "yyyy-MM-dd hh:mm:ss",};

    @Enumerated(EnumType.STRING)
    @Column(name = "data_type")
    private DataType dataType;

    @Column(name = "obj_value")
    private String value = "";

    /**
     * Create a string value
     *
     * @param value String value
     * @return The value of a string type
     */
    public static Value stringValue(String value) {
        return new Value(DataType.STRING, value);
    }

    /**
     * Creating an integer value
     *
     * @param value String value
     * @return The value of an integer type
     */
    public static Value intValue(String value) {
        return new Value(DataType.INT, value);
    }

    /**
     * Create a long integer value
     *
     * @param value String value
     * @return The value of a long integer type
     */
    public static Value longValue(String value) {
        return new Value(DataType.LONG, value);
    }

    /**
     * Create a floating point value
     *
     * @param value String value
     * @return A value of type decimal
     */
    public static Value doubleValue(String value) {
        return new Value(DataType.DOUBLE, value);
    }

    /**
     * Create a BigDecimal value
     *
     * @param value String value
     * @return A value of type BigDecimal
     */
    public static Value bigDecimalValue(String value) {
        return new Value(DataType.BIG_DECIMAL, value);
    }

    /**
     * Create a Boolean value
     *
     * @param value String value
     * @return A boolean value
     */
    public static Value booleanValue(String value) {
        return new Value(DataType.BOOLEAN, value);
    }

    /**
     * Creation date type value (without time part)
     *
     * @param value String value
     * @return The value of a date type
     */
    public static Value dateValue(String value) {
        return new Value(DataType.DATE, value);
    }

    /**
     * Create a time type value (no date part)
     *
     * @param value String value
     * @return The value of a time type
     */
    public static Value timeValue(String value) {
        return new Value(DataType.TIME, value);
    }

    /**
     * Create a timestamp type value (including the date and time parts)
     *
     * @param value String value
     * @return A value of type timestamp
     */
    public static Value dateTimeValue(String value) {
        return new Value(DataType.DATE_TIME, value);
    }

    protected Value() {
    }

    private Value(DataType dataType, String value) {
        this.dataType = dataType;
        if (value != null) {
            this.value = value;
        }

    }

    /**
     * Get Data Types
     * @return Data type value belongs
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     * Get The original string value
     * @return The original string value
     */
    public String getStringValue() {
        return value;
    }

    /**
     * Get String value
     * @return String value
     */
    public String getString() {
        return (String) DataType.STRING.getValue(value);
    }

    /**
     * Get Integer
     * @return Integer values
     */
    public int getInt() {
        return (Integer) DataType.INT.getValue(value);
    }

    /**
     * Get Long integer value
     * @return Long integer value
     */
    public long getLong() {
        return (Long) DataType.LONG.getValue(value);
    }
    
    /**
     * Get Floating-point values
     * @return Floating point values
     */
    public double getDouble() {
        return (Double) DataType.DOUBLE.getValue(value);
    }

    /**
     * GetBigDecimal Value
     * @return BigDecimal In the form of value.
     */
    public BigDecimal getBigDecimal() {
        return (BigDecimal) DataType.BIG_DECIMAL.getValue(value);
    }

    /**
     * Get Boolean value
     * @return Boolean values form
     */
    public boolean getBoolean() {
        return (Boolean) DataType.BOOLEAN.getValue(value);
    }

    /**
     * Get Date / time values
     * @return Date in the form of value
     */
    public Date getDate() {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            return DateUtils.parseDateStrictly(value, DATE_TIME_FORMAT);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converted to the corresponding values obtained data types
     * @return Converted from the original string value of the corresponding data type.
     */
    public Object getValue() {
        return dataType.getValue(value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(dataType).append(value).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Value)) {
            return false;
        }
        Value that = (Value) other;
        return new EqualsBuilder()
                .append(this.getDataType(), that.getDataType())
                .append(this.getStringValue(), that.getStringValue())
                .isEquals();
    }

    @Override
    public String toString() {
        return value;
    }

}
