/**
 * 
 */
package org.solid.builder;

public class Account  {
	// compulsory parameters
	private final int tradeDate;
	// optional parameters
	private String grandParentNum;
	private String slangMnc;
	private String actMnc;
	private String grandParentName;
	private String acctIdSource;

    
	public Account(int tradeDate) {
		super();
		this.tradeDate = tradeDate;
	}

	public String getAcctIdSource() {
		return acctIdSource;
	}


	public String getGrandParentNum() {
		return grandParentNum;
	}

	public void setGrandParentNum(String grandParentNum) {
		this.grandParentNum = grandParentNum;
	}

	public String getSlangMnc() {
		return slangMnc;
	}

	public void setSlangMnc(String slangMnc) {
		this.slangMnc = slangMnc;
	}

	public String getActMnc() {
		return actMnc;
	}

	public void setActMnc(String actMnc) {
		this.actMnc = actMnc;
	}

	public String getGrandParentName() {
		return grandParentName;
	}
	
	public void setGrandParentName(String grandParentName) {
		this.grandParentName = grandParentName;
	}
	
	public int getTradeDate() {
		return tradeDate;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acctIdSource == null) ? 0 : acctIdSource.hashCode());
		result = prime * result + ((actMnc == null) ? 0 : actMnc.hashCode());
		result = prime * result + ((slangMnc == null) ? 0 : slangMnc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (acctIdSource == null) {
			if (other.acctIdSource != null)
				return false;
		} else if (!acctIdSource.equals(other.acctIdSource))
			return false;
		if (actMnc == null) {
			if (other.actMnc != null)
				return false;
		} else if (!actMnc.equals(other.actMnc))
			return false;
		if (slangMnc == null) {
			if (other.slangMnc != null)
				return false;
		} else if (!slangMnc.equals(other.slangMnc))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Account [grandParentNum=" + grandParentNum + ", slangMnc=" + slangMnc + ", actMnc=" + actMnc
				+ ", grandParentName=" + grandParentName + ", acctIdSource=" + acctIdSource + ", tradeDate=" + tradeDate
				+ "]";
	}


	public static class AccountBuilder{
		private String slangMnc;
		private String actMnc;
		private String acctIdSource;
		private int tradeDate;
		
		public AccountBuilder(String acctIdSource, int tradeDate ) {
			this.acctIdSource = acctIdSource;
			this.tradeDate = tradeDate;
		}
		
		public AccountBuilder slang(String slangMnc) {
			this.slangMnc = slangMnc;
			return this;
		}
		
		public AccountBuilder actmnc(String actMnc) {
			this.actMnc = actMnc;
			return this;
		}
		public AccountBuilder tradeDate(int tradeDate) {
			this.tradeDate = tradeDate;
			return this;
		}
		
		public Account build() {
			return new Account(this);
		}
		
	}
	

	public void setAcctIdSource(String acctIdSource) {
		this.acctIdSource = acctIdSource;
		
	}
	
	private Account(AccountBuilder accountBuilder) {
		this(accountBuilder.tradeDate);
		slangMnc = accountBuilder.slangMnc;
		actMnc = accountBuilder.actMnc;
		acctIdSource = accountBuilder.acctIdSource;
	}
	
}
