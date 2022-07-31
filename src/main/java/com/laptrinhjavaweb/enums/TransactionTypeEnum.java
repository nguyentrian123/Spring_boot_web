package com.laptrinhjavaweb.enums;

public enum TransactionTypeEnum {
	

	 	QUA_TRINH_CSKH("Qúa trình CSKH"),
		DAN_DI_XEM("Dẫn đi xem");
	    private final String transactionTypeValue;

	    TransactionTypeEnum(String transactionTypeValue) {
	        this.transactionTypeValue = transactionTypeValue;
	    }

		public String getTransactionTypeValue() {
			return transactionTypeValue;
		}
	
}
