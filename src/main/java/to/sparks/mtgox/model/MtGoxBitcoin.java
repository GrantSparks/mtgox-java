package to.sparks.mtgox.model;

import java.math.BigDecimal;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.MonetaryOperator;

/**
 * A representation of bitcoins as used with MtGox
 *
 * @author SparksG
 */
public class MtGoxBitcoin extends MtGoxUnitOfCredit {

    /*
     * A convenience method for creating Bitcoin units of currency. Not
     * recommended though because you shouldn't really be storing monetary
     * values as doubles anyway. Use BigDecimal instead.
     */
    public MtGoxBitcoin(double float_value) {
        super(float_value, CurrencyInfo.BitcoinCurrencyInfo);
    }

    /*
     * Create bitcoin units of currency This is an attempt to be directly
     * compatible with the MtGox API price_int parameter, which has variable
     * scales depending on currency code. See
     * https://en.bitcoin.it/wiki/MtGox/API#Number_Formats
     */
    public MtGoxBitcoin(long int_value) {
        super(int_value, CurrencyInfo.BitcoinCurrencyInfo);
    }

    public MtGoxBitcoin(BigDecimal value) {
        super(value, CurrencyInfo.BitcoinCurrencyInfo);
    }

	@Override
	public MonetaryAmount abs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount add(MonetaryAmount arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount add(Number arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T asType(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount divide(MonetaryAmount arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount divide(Number arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount[] divideAndRemainder(MonetaryAmount arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount[] divideAndRemainder(Number arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount divideToIntegralValue(MonetaryAmount arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount divideToIntegralValue(Number arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount from(Number arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount from(CurrencyUnit arg0, Number arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getNumberType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPrecision() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScale() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEqualTo(MonetaryAmount arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGreaterThan(MonetaryAmount arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGreaterThanOrEqualTo(MonetaryAmount arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLessThan(MonetaryAmount arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLessThanOrEqualTo(MonetaryAmount arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNegative() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNegativeOrZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNotEqualTo(MonetaryAmount arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPositive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPositiveOrZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MonetaryAmount multiply(MonetaryAmount arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount multiply(Number arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount negate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount plus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount pow(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount remainder(MonetaryAmount arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount remainder(Number arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount scaleByPowerOfTen(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public short shortValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public short shortValueExact() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int signum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MonetaryAmount subtract(MonetaryAmount arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount subtract(Number arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount ulp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryAmount with(MonetaryOperator arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
