package hr.fer.zemris.apr.lab4.krizanje;




public class SinglePoint extends Krizanje {
	
	
	@Override
	public Double krizaj(double prvi,double drugi,int n,int point){
	
		String prvaMaska="";
		String drugaMaska="";
		for(int i=0;i<n;i++){
			if(i<point){
				prvaMaska+="1";
				drugaMaska+="0";
				
			}else{
				prvaMaska+="0";
				drugaMaska+="1";
			}
		}
		Integer pm=Integer.parseInt(prvaMaska, 2);
		Integer dm=Integer.parseInt(drugaMaska,2);
		return (((int)prvi & pm) | ((int)drugi & dm))*1.0;
	}
	
	

}
