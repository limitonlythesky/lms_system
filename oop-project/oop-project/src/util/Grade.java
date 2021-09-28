package util;

import java.io.Serializable;

public class Grade implements Serializable{

	private static final long serialVersionUID = -4473137785938250551L;
	private String textGrade;
	private Double gpa;
	
	public Grade(String textGrade, Double gpa) {
		this.textGrade = textGrade;
		this.gpa = gpa;
	}

	public String getTextGrade() {
		return textGrade;
	}

	public void setTextGrade(String textGrade) {
		this.textGrade = textGrade;
	}

	public Double getGpa() {
		return gpa;
	}

	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gpa == null) ? 0 : gpa.hashCode());
		result = prime * result + ((textGrade == null) ? 0 : textGrade.hashCode());
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
		Grade other = (Grade) obj;
		if (gpa == null) {
			if (other.gpa != null)
				return false;
		} else if (!gpa.equals(other.gpa))
			return false;
		if (textGrade == null) {
			if (other.textGrade != null)
				return false;
		} else if (!textGrade.equals(other.textGrade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grade [textGrade=" + textGrade + ", gpa=" + gpa + "]";
	}
	
	
}
