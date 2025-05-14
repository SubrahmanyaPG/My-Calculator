import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
    int borderwidth=360;
    int borderheight=540;

    Color customlightgrey=new Color(212,212,210);
    Color customdarkgrey=new Color(80,80,80);
    Color customblack=new Color(28,28,28);
    Color customorange=new Color(255,149,0);

    JFrame frame=new JFrame("MY CALCULATOR");
    JLabel displaylable=new JLabel();
    JPanel displaypanel=new JPanel();
    JPanel buttonspanel=new JPanel();

    String A="0";
    String operator=null;
    String B=null;

    String[] buttonvalues = {
            "AC","+/-","%","÷",
            "7","8","9","x",
            "4","5","6","-",
            "1","2","3","+",
            "0",".","√","="
    };
    String[] rightsymbol={"÷","x","-","+","="};
    String[] topsymbol={"AC","+/-","%"};

    Calculator(){
        frame.setVisible(true);
        frame.setSize(borderwidth,borderheight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        displaylable.setBackground(customblack);
        displaylable.setForeground(Color.white);
        displaylable.setFont(new Font("arial",Font.PLAIN,70));
        displaylable.setHorizontalAlignment(JLabel.RIGHT);
        displaylable.setText("0");
        displaylable.setOpaque(true);

        displaypanel.setLayout(new BorderLayout());
        displaypanel.add(displaylable);
        frame.add(displaypanel, BorderLayout.NORTH);

        buttonspanel.setBackground(customblack);
        buttonspanel.setLayout(new GridLayout(5,4));
        frame.add(buttonspanel);

        for(int i=0;i<buttonvalues.length;i++){
            JButton button=new JButton();
            String buttonvalue=buttonvalues[i];
            button.setText(buttonvalue);
            button.setFont(new Font("Arial",Font.PLAIN,35));
            button.setFocusable(false);//it donot show the box on text of button
            button.setBorder(new LineBorder(Color.BLACK));
            if(Arrays.asList(topsymbol).contains(buttonvalue)){
                button.setBackground(customlightgrey);
                button.setForeground(customblack);
            } else if (Arrays.asList(rightsymbol).contains(buttonvalue)) {
                button.setBackground(customorange);
                button.setForeground(Color.white);
            }else{
                button.setBackground(customdarkgrey);
                button.setForeground(Color.white);
            }
            buttonspanel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button =(JButton) e.getSource();
                    String buttonvalue = button.getText();
                    if(Arrays.asList(rightsymbol).contains(buttonvalue)){
                        if(buttonvalue=="="){
                            if(A!=null){
                                B=displaylable.getText();
                                double numA=Double.parseDouble(A);
                                double numB=Double.parseDouble(B);

                                if(operator=="+"){
                                    displaylable.setText(removezerodecimal(numA+numB));
                                } else if (operator=="-") {
                                    displaylable.setText(removezerodecimal(numA-numB));
                                }else if (operator=="x") {
                                    displaylable.setText(removezerodecimal(numA*numB));
                                }else if (operator=="÷") {
                                    displaylable.setText(removezerodecimal(numA/numB));
                                }
                                clearall();
                            }

                        } else if ("+-x÷".contains(buttonvalue)) {
                            if(operator==null){
                                A=displaylable.getText();
                                displaylable.setText("0");
                                B="0";
                            }
                            operator=buttonvalue;

                        }

                    } else if (Arrays.asList(topsymbol).contains(buttonvalue)) {
                        if(buttonvalue=="AC"){
                            clearall();
                            displaylable.setText("0");

                        } else if (buttonvalue=="+/-") {
                            double numdisplay=Double.parseDouble(displaylable.getText());
                            numdisplay*=-1;
                            displaylable.setText(removezerodecimal(numdisplay));

                        }else if(buttonvalue=="%"){
                            double numdisplay=Double.parseDouble(displaylable.getText());
                            numdisplay/=100;
                            displaylable.setText(removezerodecimal(numdisplay));

                        }

                    }else{ //digits or .
                        if(buttonvalue=="."){
                            if(!displaylable.getText().contains(buttonvalue)){
                                displaylable.setText(displaylable.getText()+buttonvalue);
                            }

                        } else if (buttonvalue=="√") {
                            double numdisplay = Double.parseDouble(displaylable.getText());
                            if(numdisplay<0){
                                displaylable.setText("Invalid");
                            }else {
                                double result=Math.sqrt(numdisplay);
                                displaylable.setText(removezerodecimal(result));
                            }

                        } else if ("0123456789".contains(buttonvalue)) {
                            if(displaylable.getText()=="0"){
                                displaylable.setText(buttonvalue);
                            }else {
                                displaylable.setText(displaylable.getText()+buttonvalue);
                            }
                        }


                    }
                }
            });

        }


    }
    void clearall(){
        A="0";
        operator=null;
        B=null;
    }

    String removezerodecimal(double numdisplay){
        if(numdisplay%1==0){
            return Integer.toString((int)numdisplay);
        }
        return Double.toString(numdisplay);
    }

}
