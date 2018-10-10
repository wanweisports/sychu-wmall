package com.wardrobe.platform.rfid.rfid.rxobserver;

/**
 * Reader setting describe object
 * @author Administrator
 *
 */
public class ReaderSetting {

	private static final ReaderSetting mReaderSetting = new ReaderSetting();

	/** Reader ID*/
	public byte btReadId;
	/** Reader major version NO*/
	public byte btMajor;
	/** Reader Minor version NO*/
	public byte btMinor;
	@Deprecated
	public byte btIndexBaudrate;
	/**Reader temperature symbol*/
	public byte btPlusMinus;
	/**Reader temperature value */
	public byte btTemperature;
	/**Output power 1,4 or 8 bytes*/
	public byte []btAryOutputPower;
	/** Work Antenna*/
	public byte btWorkAntenna;
	@Deprecated
	public byte btDrmMode;
	/** Frequency Region*/
	public byte btRegion;
	/** Frequency Start value*/
	public byte btFrequencyStart;
	/** Frequency End value*/
	public byte btFrequencyEnd;
	/** Beeper Mode*/
	public byte btBeeperMode;
	/** Reader GPI01 */
	public byte btGpio1Value;
	/** Reader GPI02 */
	public byte btGpio2Value;
	/** Reader GPI03 */
	public byte btGpio3Value;
	/** Reader GPI04 */
	public byte btGpio4Value;
	
	/**Antenna sensitivity*/
	public byte btAntDetector;
	/** Monza TID switch status*/
	public byte btMonzaStatus;
	/** Monza Whether save the status*/
	public boolean blnMonzaStore;
	/**Identifier fixed 12 bytes*/
	public byte []btAryReaderIdentifier;
	/** Return loss */
	public byte btReturnLoss;
	@Deprecated
	public byte btImpedanceFrequency;
	
	/** Custom starting frequency*/
	public int nUserDefineStartFrequency;
	/** Custom channel spacing*/
	public byte btUserDefineFrequencyInterval;
	/**  custom number frequency point */
	public byte btUserDefineChannelQuantity;
	/** Rf communications link*/
	public byte btRfLinkProfile;
	/** match epc value*/
	public String mMatchEpcValue;

	/**
     * Get the single ReaderSetting instance.
	 * @return the single ReaderSetting instance.
	 */
	static ReaderSetting newInstance() {
		return mReaderSetting;
	}

	private ReaderSetting() {
		btReadId = (byte) 0xFF;
		btMajor = 0x00;
		btMinor = 0x00;
		btIndexBaudrate = 0x00;
		btPlusMinus = 0x00;
		btTemperature = 0x00;
		btAryOutputPower = null;
		btWorkAntenna = 0x00;
		btDrmMode = 0x00;
		btRegion = 0x00;
		btFrequencyStart = 0x00;
		btFrequencyEnd = 0x00;
		btBeeperMode = 0x00;
		blnMonzaStore = false;
		btGpio1Value = 0x00;
		btGpio2Value = 0x00;
		btGpio3Value = 0x00;
		btGpio4Value = 0x00;
		btAntDetector = 0x00;
		btMonzaStatus = 0x00;
		btAryReaderIdentifier = new byte[12];
		btReturnLoss = 0x00;
		btImpedanceFrequency = 0x00;
		nUserDefineStartFrequency = 0x00;
		btUserDefineFrequencyInterval = 0x00;
		btUserDefineChannelQuantity = 0x00;
		btRfLinkProfile = 0x00;
		mMatchEpcValue = null;
	}
	
	/**
	 * Wrapper class of mask query information.
	 */
	public static class MaskMap {
        /** you can set 1-5 mask, this is the mask id*/
		public byte mMaskID;
		/** The quantity of setting mask*/
		public byte mMaskQuantity;
		/** 0x00	Inventoried S0
		    0x01	Inventoried S1
		 	0x02	Inventoried S2
		 	0x03	Inventoried S3
		 	0x04	SL
		 */
		public byte mTarget;
		/** 	  Tag Matches Mask 	           |     Tag Doesn¡¯t Match Mask
		 0x00	Assert SL or inventoried ->A   |	Deassert SL or inventoried ->B
		 0x01	Assert SL or inventoried ->A   |	Do nothing
		 0x02	Do nothing	                   |    Deassert SL or inventoried->B
		 0x03	Negate SL or(A->B,B->A)	       |    Do nothing
		 0x04	Deassert SL or inventoried ->B |	Assert SL or inventoried->A
		 0x05	Deaaert SL or inventoried ->B  |	Do nothing
		 0x06	Do nothing	                   |    Assert SL or inventoried ->A
		 0x07	Do nothing	                   |    Negate SL or (A->B, B->A)
		 */
		public byte mAction;
		/**0x00	Reserved for Future use
		   0x01	EPC
		   0x02	TID
		   0x03	USER
		 */
		public byte mMembank;
		/** Start mask address,unit is bit*/
		public byte mStartMaskAdd;
		/** Mask length, unit is bit*/
		public byte mMaskBitLen;
		/**You need not complete mask all the value,it according the {@link #mMaskValue}*/
		public byte[] mMaskValue;
		/** Reserved */
		public byte mTruncate;
		public MaskMap(byte[] data) {
			this.mMaskID =data[0];
			this.mMaskQuantity = data[1];
			this.mTarget = data[2];
			this.mAction = data[3];
			this.mMembank = data[4];
			this.mStartMaskAdd = data[5];
			this.mMaskBitLen = data[6];
			this.mMaskValue = new byte[data.length - 8];
			System.arraycopy(data, 7, this.mMaskValue, 0, mMaskValue.length);
			this.mTruncate = data[data.length - 1];
		}
		
		@Override
		public boolean equals(Object obj) {
			return this.mMaskID == ((MaskMap)obj).mMaskID;
		}
	}
}
