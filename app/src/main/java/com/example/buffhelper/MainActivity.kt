/*

Buff Helper Tool
not associated with netease or buff163


Font - The Nunito Project Authors (https://github.com/googlefonts/nunito)
This Font Software is licensed under the SIL Open Font License, Version 1.1.


 */

package com.example.buffhelper


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val window: Window = this@MainActivity.window
        window.navigationBarColor = ContextCompat.getColor(this@MainActivity, R.color.midnight)




        var AfterTax1: Double
        var AfterTax2 : Double
        var AfterTax3 : Double
        var AfterTax4 : Double


        fun FontColorApply(Serial:Int,Return:Float){
            var ColorApply:String = ""

            when{
                Return <= -1.0 -> ColorApply = "#931818" //NegRed
                Return >= -1.0 && Return < 0.0 -> ColorApply = "#E43838" //BadRed
                Return >= 0.0 && Return < 1.0 -> ColorApply = "#D1AA32" //MidYellow
                Return >= 1.0 && Return < 2.5 -> ColorApply = "#9BF18C" //DecentGreen
                Return >= 2.5 && Return < 5.0 -> ColorApply = "#2EFF09" //GoodGreen
                Return >= 5.0 && Return < 10.0 -> ColorApply = "#13FFF7" //AmazingCyan
                else -> ColorApply = "#C742FF" //GodlyPink
            }

            when{
                Serial == 1 ->{
                    ROI1.setTextColor(Color.parseColor(ColorApply))
                    Profit1.setTextColor(Color.parseColor(ColorApply))
                }
                Serial == 2 ->{
                    ROI2.setTextColor(Color.parseColor(ColorApply))
                    Profit2.setTextColor(Color.parseColor(ColorApply))
                }
                Serial == 3 ->{
                    ROI3.setTextColor(Color.parseColor(ColorApply))
                    Profit3.setTextColor(Color.parseColor(ColorApply))
                }
                Serial == 4 ->{
                    ROI4.setTextColor(Color.parseColor(ColorApply))
                    Profit4.setTextColor(Color.parseColor(ColorApply))
                }
            }

        }

        fun RoiCalc(Buy: Float, AT: Float): Float {
            val Profit: Float = AT - Buy
            return (Profit / Buy * 100)
        }

        val df = DecimalFormat() //decimalformat used for converting numbers to 2 decimal digits,
        df.setMaximumFractionDigits(2);


        SellValue1.addTextChangedListener {
            if (!(SellValue1.text.toString().trim().equals(""))) {

                AfterTax1 = SellValue1.text.toString().toFloat()*0.975
                AT1.text = "AT: "+df.format(AfterTax1)
                Tax1.text = "Tax: "+df.format(SellValue1.text.toString().toFloat()*0.025)

            if(!(SellValue1.text.toString().trim().equals("")) && !(BuyValue1.text.toString().trim().equals(""))){
                Profit1.text = "Prof: "+df.format(AfterTax1 - BuyValue1.text.toString().toFloat())
                ROI1.text = "ROI: "+df.format(RoiCalc(BuyValue1.text.toString().toFloat(),AfterTax1.toFloat()))+"%"

                FontColorApply(1,RoiCalc(BuyValue1.text.toString().toFloat(),AfterTax1.toFloat()))
            }

            }

        BuyValue1.addTextChangedListener {

            if (!(SellValue1.text.toString().trim().equals(""))) {

                AfterTax1 = SellValue1.text.toString().toFloat()*0.975
                AT1.text = "AT: "+df.format(AfterTax1)
                Tax1.text = "Tax: "+df.format(SellValue1.text.toString().toFloat()*0.025)

                if(!(SellValue1.text.toString().trim().equals("")) && !(BuyValue1.text.toString().trim().equals(""))){

                    Profit1.text = "Prof: "+df.format(AfterTax1 - BuyValue1.text.toString().toFloat())
                    ROI1.text = "ROI: "+df.format(RoiCalc(BuyValue1.text.toString().toFloat(),AfterTax1.toFloat()))+"%"

                    FontColorApply(1,RoiCalc(BuyValue1.text.toString().toFloat(),AfterTax1.toFloat()))
                }
            }
        }

        }

        SellValue2.addTextChangedListener {
            if (!(SellValue2.text.toString().trim().equals(""))) {

                AfterTax2 = SellValue2.text.toString().toFloat()*0.975
                AT2.text = "AT: "+df.format(AfterTax2)
                Tax2.text = "Tax: "+df.format(SellValue2.text.toString().toFloat()*0.025)

                if(!(SellValue2.text.toString().trim().equals("")) && !(BuyValue2.text.toString().trim().equals(""))){
                    Profit2.text = "Prof: "+df.format(AfterTax2 - BuyValue2.text.toString().toFloat())
                    ROI2.text = "ROI: "+df.format(RoiCalc(BuyValue2.text.toString().toFloat(),AfterTax2.toFloat()))+"%"

                    FontColorApply(2,RoiCalc(BuyValue2.text.toString().toFloat(),AfterTax2.toFloat()))
                }

            }

            BuyValue2.addTextChangedListener {

                if (!(SellValue2.text.toString().trim().equals(""))) {

                    AfterTax2 = SellValue2.text.toString().toFloat()*0.975
                    AT2.text = "AT: "+df.format(AfterTax2)
                    Tax2.text = "Tax: "+df.format(SellValue2.text.toString().toFloat()*0.025)

                    if(!(SellValue2.text.toString().trim().equals("")) && !(BuyValue2.text.toString().trim().equals(""))){

                        Profit2.text = "Prof: "+df.format(AfterTax2 - BuyValue2.text.toString().toFloat())
                        ROI2.text = "ROI: "+df.format(RoiCalc(BuyValue2.text.toString().toFloat(),AfterTax2.toFloat()))+"%"

                        FontColorApply(2,RoiCalc(BuyValue2.text.toString().toFloat(),AfterTax2.toFloat()))
                    }
                }
            }

        }

        SellValue3.addTextChangedListener {
            if (!(SellValue3.text.toString().trim().equals(""))) {

                AfterTax3 = SellValue3.text.toString().toFloat()*0.975
                AT3.text = "AT: "+df.format(AfterTax3)
                Tax3.text = "Tax: "+df.format(SellValue3.text.toString().toFloat()*0.025)

                if(!(SellValue3.text.toString().trim().equals("")) && !(BuyValue3.text.toString().trim().equals(""))){
                    Profit3.text = "Prof: "+df.format(AfterTax3 - BuyValue3.text.toString().toFloat())
                    ROI3.text = "ROI: "+df.format(RoiCalc(BuyValue3.text.toString().toFloat(),AfterTax3.toFloat()))+"%"

                    FontColorApply(3,RoiCalc(BuyValue3.text.toString().toFloat(),AfterTax3.toFloat()))
                }

            }

            BuyValue3.addTextChangedListener {

                if (!(SellValue3.text.toString().trim().equals(""))) {

                    AfterTax3 = SellValue3.text.toString().toFloat()*0.975
                    AT3.text = "AT: "+df.format(AfterTax3)
                    Tax3.text = "Tax: "+df.format(SellValue3.text.toString().toFloat()*0.025)

                    if(!(SellValue3.text.toString().trim().equals("")) && !(BuyValue3.text.toString().trim().equals(""))){

                        Profit3.text = "Prof: "+df.format(AfterTax3 - BuyValue3.text.toString().toFloat())
                        ROI3.text = "ROI: "+df.format(RoiCalc(BuyValue3.text.toString().toFloat(),AfterTax3.toFloat()))+"%"

                        FontColorApply(3,RoiCalc(BuyValue3.text.toString().toFloat(),AfterTax3.toFloat()))
                    }
                }
            }

        }

        SellValue4.addTextChangedListener {
            if (!(SellValue4.text.toString().trim().equals(""))) {

                AfterTax4 = SellValue4.text.toString().toFloat()*0.975
                AT4.text = "AT: "+df.format(AfterTax4)
                Tax4.text = "Tax: "+df.format(SellValue4.text.toString().toFloat()*0.025)

                if(!(SellValue4.text.toString().trim().equals("")) && !(BuyValue4.text.toString().trim().equals(""))){
                    Profit4.text = "Prof: "+df.format(AfterTax4 - BuyValue4.text.toString().toFloat())
                    ROI4.text = "ROI: "+df.format(RoiCalc(BuyValue4.text.toString().toFloat(),AfterTax4.toFloat()))+"%"

                    FontColorApply(4,RoiCalc(BuyValue4.text.toString().toFloat(),AfterTax4.toFloat()))
                }

            }

            BuyValue4.addTextChangedListener {

                if (!(SellValue4.text.toString().trim().equals(""))) {

                    AfterTax4 = SellValue4.text.toString().toFloat()*0.975
                    AT4.text = "AT: "+df.format(AfterTax4)
                    Tax4.text = "Tax: "+df.format(SellValue4.text.toString().toFloat()*0.025)

                    if(!(SellValue4.text.toString().trim().equals("")) && !(BuyValue4.text.toString().trim().equals(""))){

                        Profit4.text = "Prof: "+df.format(AfterTax4 - BuyValue4.text.toString().toFloat())
                        ROI4.text = "ROI: "+df.format(RoiCalc(BuyValue4.text.toString().toFloat(),AfterTax4.toFloat()))+"%"

                        FontColorApply(4,RoiCalc(BuyValue4.text.toString().toFloat(),AfterTax4.toFloat()))
                    }
                }
            }

        }

        discordlogo.setOnClickListener(){
            /*clipboard.setPrimaryClip(clip)
            discordlink.show()
            */

            val discordinvite: Uri = Uri.parse("https://www.discord.com/invite/k2mDMQaTSN")
            val openinvite = Intent(Intent.ACTION_VIEW, discordinvite)
            startActivity(openinvite)

        }




    }
}