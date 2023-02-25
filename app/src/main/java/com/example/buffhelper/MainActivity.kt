/*

Buff Helper Tool
not associated with netease or buff163


Font - The Nunito Project (https://github.com/googlefonts/nunito)


 */

package com.example.buffhelper


import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val window: Window = this@MainActivity.window
        window.navigationBarColor = ContextCompat.getColor(this@MainActivity, R.color.midnight)


        var AfterTax1 : BigDecimal
        var AfterTax2 : Double
        var AfterTax3 : Double
        var AfterTax4 : Double


        fun FontColorApply(Serial:Int,Return:Float){
            var ColorApply:String

            ColorApply = when{
                Return <= -2.0 -> "#931818"
                Return >= -2.0 && Return < 0.0 -> "#E43838"
                Return >= 0.0 && Return < 1.0 -> "#D1AA32"
                Return >= 1.0 && Return < 2.5 -> "#9BF18C"
                Return >= 2.5 && Return < 5.0 -> "#2EFF09"
                Return >= 5.0 && Return < 10.0 -> "#13FFF7"
                else -> "#C742FF"
            }

            when{
                Serial == 1 ->{
                    ROI1VAL.setTextColor(Color.parseColor(ColorApply))
                    ROI1P.setTextColor(Color.parseColor(ColorApply))
                    ROI1.setTextColor(Color.parseColor(ColorApply))
                    Profit1VAL.setTextColor(Color.parseColor(ColorApply))
                    Profit1.setTextColor(Color.parseColor(ColorApply))
                }
                Serial == 2 ->{
                    ROI2VAL.setTextColor(Color.parseColor(ColorApply))
                    ROI2.setTextColor(Color.parseColor(ColorApply))
                    ROI2P.setTextColor(Color.parseColor(ColorApply))
                    Profit2VAL.setTextColor(Color.parseColor(ColorApply))
                    Profit2.setTextColor(Color.parseColor(ColorApply))
                }
                Serial == 3 ->{
                    ROI3VAL.setTextColor(Color.parseColor(ColorApply))
                    ROI3.setTextColor(Color.parseColor(ColorApply))
                    ROI3P.setTextColor(Color.parseColor(ColorApply))
                    Profit3VAL.setTextColor(Color.parseColor(ColorApply))
                    Profit3.setTextColor(Color.parseColor(ColorApply))
                }
                Serial == 4 ->{
                    ROI4VAL.setTextColor(Color.parseColor(ColorApply))
                    ROI4.setTextColor(Color.parseColor(ColorApply))
                    ROI4P.setTextColor(Color.parseColor(ColorApply))
                    Profit4VAL.setTextColor(Color.parseColor(ColorApply))
                    Profit4.setTextColor(Color.parseColor(ColorApply))
                }
            }
        }


        val df = DecimalFormat()
        df.setMaximumFractionDigits(2);

        fun RoiCalc(Buy: Float, AT: Float): Float {
            val Profit: Float = AT - Buy
            return (Profit / Buy * 100)
        }

        fun TotalAT(vararg at:TextView) {
            var sum = 0.0
            var atval = 0.0
            var temp:String

            for(TextView in at){
                try{
                    temp = TextView.text.toString().replace(Regex("\\s"), "")
                    atval = temp.replace(",",".").toDouble()
                }catch (e: NumberFormatException){
                    atval = 0.0
                }

                sum += atval
            }


            val sumdf = (sum*100).toInt().toFloat()/100


            if(sum != 0.0){
                AT_total.text = "AT: "+sumdf.toString()

            }else{
                AT_total.text = "AT:"
            }

        }



        fun TotalProfit(vararg at:TextView) {
            var sum = 0.0
            var atval:Double
            var temp:String

            for(TextView in at){
                try{
                    temp = TextView.text.toString().replace(Regex("\\s"), "")
                    atval = temp.replace(",",".").toDouble()
                }catch (e: NumberFormatException){
                    atval = 0.0
                }

                sum += atval
            }
            val sumdf = df.format(sum)


            if(sum != 0.0){
                Profit_total.text = "Profit: "+sumdf.toString()
            }else{
                Profit_total.text = "Profit:"
            }



        }

        fun TotalROI(vararg editTexts:EditText){
            var sum = 0.0
            var atval:Float
            var temp:String
            var res = 0.0
            var cheese = 0

            for (editText in editTexts) {
                try {
                    atval = editText.text.toString().trim().toFloat()
                    sum += atval
                } catch (e: NumberFormatException) {

                }


                try {
                    res = (Profit_total.text.toString().substring(8).replace(",", "").replace(Regex("\\s"), "").toFloat() / sum) * 100
                }catch(e:IndexOutOfBoundsException){
                    res = 0.0
                }


            }
            val sumdf = df.format(res)

            var ColorApply:String

            ColorApply = when{
                res <= -2.0 -> "#931818"
                res >= -2.0 && res < 0.0 -> "#E43838"
                res >= 0.0 && res < 1.0 -> "#D1AA32"
                res >= 1.0 && res < 2.5 -> "#9BF18C"
                res >= 2.5 && res < 5.0 -> "#2EFF09"
                res >= 5.0 && res < 10.0 -> "#13FFF7"
                else -> "#C742FF"
            }


            if(res != 0.0){
                ROI_total.text = "ROI: "+sumdf.toString()+"%"
                ROI_total.setTextColor(Color.parseColor(ColorApply))
                Profit_total.setTextColor(Color.parseColor(ColorApply))

            }else{
                ROI_total.text = "ROI:"
                ROI_total.setTextColor(Color.parseColor("#C3C3C3"))
                Profit_total.setTextColor(Color.parseColor("#C3C3C3"))
            }

        }



        fun TotalTAX(vararg at:TextView) {
            var sum = 0.0
            var atval:Double
            var temp:String

            for(TextView in at){
                try{
                    temp = TextView.text.toString().replace(Regex("\\s"), "")
                    atval = temp.replace(",",".").toDouble()
                }catch (e: NumberFormatException){
                    atval = 0.0
                }

                sum += atval
            }
            val sumdf = (sum*100).toInt().toFloat()/100

            if(sum != 0.0){
                Tax_total.text = "Tax: "+sumdf.toString()
            }else{
                Tax_total.text = "Tax:"
            }

        }


        SellValue1.addTextChangedListener {
            if (!(SellValue1.text.toString().trim().equals("")) && SellValue1.text.toString() != ".") {

                AfterTax1 = BigDecimal(SellValue1.text.toString()).multiply(BigDecimal("0.975"))

                if((BigDecimal(SellValue1.text.toString()).multiply(BigDecimal("0.025"))).toString().substringAfter(".").trimEnd('0').length>2){
                    Tax1VAL.text = (BigDecimal(SellValue1.text.toString()).multiply(BigDecimal("0.025"))).setScale(2,RoundingMode.CEILING).toString()
                }else{
                    Tax1VAL.text = (BigDecimal(SellValue1.text.toString()).multiply(BigDecimal("0.025"))).setScale(2).toString()
                }

                AT1VAL.text = (BigDecimal(SellValue1.text.toString()).subtract(BigDecimal(Tax1VAL.text.toString()))).setScale(2,RoundingMode.FLOOR).toString()

                TotalAT(AT1VAL,AT2VAL,AT3VAL,AT4VAL)
                TotalTAX(Tax1VAL,Tax2VAL,Tax3VAL,Tax4VAL)

                if(!(BuyValue1.text.toString().trim().equals("")) && BuyValue1.text.toString() != "." && BuyValue1.text.toString() != "0"){

                    Profit1VAL.text = (BigDecimal(AT1VAL.text.toString()).subtract(BigDecimal(BuyValue1.text.toString()))).toString()

                    ROI1VAL.text = df.format(RoiCalc(BuyValue1.text.toString().toFloat(),AfterTax1.toFloat()))
                    ROI1P.text = "%"
                    FontColorApply(1,RoiCalc(BuyValue1.text.toString().toFloat(),AfterTax1.toFloat()))
                    TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                    TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)
                }
            }else{
                AT1VAL.text = ""
                Tax1VAL.text = ""
                ROI1VAL.text = ""
                Profit1VAL.text = ""
                ROI1P.text = ""
                ROI1.setTextColor(Color.parseColor("#C3C3C3"))
                Profit1.setTextColor(Color.parseColor("#C3C3C3"))
                TotalAT(AT1VAL,AT2VAL,AT3VAL,AT4VAL)
                TotalTAX(Tax1VAL,Tax2VAL,Tax3VAL,Tax4VAL)
                TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)

            }


            //why does the first part use .equals() when the other uses !=, well you see I'm too lazy to change it so instead I'm making a comment here to address this inconsistency
            BuyValue1.addTextChangedListener {
                if(!(SellValue1.text.toString().trim().equals("")) && !(BuyValue1.text.toString().trim().equals("")) && BuyValue1.text.toString() != "." && SellValue1.text.toString() != "."){
                    AfterTax1 = BigDecimal(SellValue1.text.toString()).multiply(BigDecimal("0.975"))


                    Profit1VAL.text = (BigDecimal(AfterTax1.toString()).subtract(BigDecimal(BuyValue1.text.toString()))).setScale(2,RoundingMode.UP).toString()
                    ROI1P.text = "%"
                    ROI1VAL.text = df.format(RoiCalc(BuyValue1.text.toString().toFloat(),AfterTax1.toFloat()))

                    FontColorApply(1,RoiCalc(BuyValue1.text.toString().toFloat(),AfterTax1.toFloat()))
                    TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                    TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)
                }else{
                    Profit1VAL.text = ""
                    ROI1VAL.text = ""
                    ROI1P.text = ""
                    ROI1.setTextColor(Color.parseColor("#C3C3C3"))
                    Profit1.setTextColor(Color.parseColor("#C3C3C3"))
                    TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                    TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)
                }
            }
        }

        SellValue2.addTextChangedListener {
            if (!(SellValue2.text.toString().trim().equals("")) && SellValue2.text.toString() != ".") {

                AfterTax2 = SellValue2.text.toString().toFloat() * 0.975
                AT2VAL.text = df.format(AfterTax2)
                Tax2VAL.text = df.format(SellValue2.text.toString().toFloat() * 0.025)
                TotalAT(AT1VAL,AT2VAL,AT3VAL,AT4VAL)
                TotalTAX(Tax1VAL,Tax2VAL,Tax3VAL,Tax4VAL)



                if (!(SellValue2.text.toString().trim().equals("")) && !(BuyValue2.text.toString().trim().equals("")) && BuyValue2.text.toString() != "." && BuyValue2.text.toString() != "0") {
                    Profit2VAL.text = df.format(AfterTax2 - BuyValue2.text.toString().toFloat())
                    ROI2VAL.text = df.format(RoiCalc(BuyValue2.text.toString().toFloat(), AfterTax2.toFloat()))
                    ROI2P.text = "%"
                    FontColorApply(2, RoiCalc(BuyValue2.text.toString().toFloat(), AfterTax2.toFloat()))
                    TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                    TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)
                }
            } else {
                AT2VAL.text = ""
                Tax2VAL.text = ""
                ROI2VAL.text = ""
                Profit2VAL.text = ""
                ROI2P.text = ""
                ROI2.setTextColor(Color.parseColor("#C3C3C3"))
                Profit2.setTextColor(Color.parseColor("#C3C3C3"))
                TotalAT(AT1VAL,AT2VAL,AT3VAL,AT4VAL)
                TotalTAX(Tax1VAL,Tax2VAL,Tax3VAL,Tax4VAL)
                TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)

            }

            BuyValue2.addTextChangedListener {

                if (!(SellValue2.text.toString().trim().equals("")) && !(BuyValue2.text.toString().trim().equals("")) && BuyValue2.text.toString() != "." && SellValue2.text.toString() != "." && SellValue2.text.toString() != "0" && BuyValue2.text.toString() != "0") {
                    AfterTax2 = SellValue2.text.toString().toFloat() * 0.975
                    Profit2VAL.text = df.format(AfterTax2 - BuyValue2.text.toString().toFloat())
                    ROI2P.text = "%"
                    ROI2VAL.text = df.format(RoiCalc(BuyValue2.text.toString().toFloat(), AfterTax2.toFloat()))
                    AT2VAL.text = df.format(AfterTax2)
                    FontColorApply(2, RoiCalc(BuyValue2.text.toString().toFloat(), AfterTax2.toFloat()))
                    TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                    TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)
                } else {
                    Profit2VAL.text = ""
                    ROI2VAL.text = ""
                    ROI2P.text = ""
                    ROI2.setTextColor(Color.parseColor("#C3C3C3"))
                    Profit2.setTextColor(Color.parseColor("#C3C3C3"))
                    TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                    TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)
                }
            }
        }

        SellValue3.addTextChangedListener {
            if (!(SellValue3.text.toString().trim().equals("")) && SellValue3.text.toString() != "." && SellValue3.text.toString() != "0") {

                AfterTax3 = SellValue3.text.toString().toFloat()*0.975
                AT3VAL.text = df.format(AfterTax3)
                Tax3VAL.text = df.format(SellValue3.text.toString().toFloat()*0.025)
                TotalAT(AT1VAL,AT2VAL,AT3VAL,AT4VAL)
                TotalTAX(Tax1VAL,Tax2VAL,Tax3VAL,Tax4VAL)


                if(!(SellValue3.text.toString().trim().equals("")) && !(BuyValue3.text.toString().trim().equals("")) && !(BuyValue3.text.toString().trim().equals("")) && BuyValue3.text.toString() != "." && BuyValue3.text.toString() != "0"){
                    Profit3VAL.text = df.format(AfterTax3 - BuyValue3.text.toString().toFloat())
                    ROI3VAL.text = df.format(RoiCalc(BuyValue3.text.toString().toFloat(),AfterTax3.toFloat()))
                    ROI3P.text = "%"
                    FontColorApply(3,RoiCalc(BuyValue3.text.toString().toFloat(),AfterTax3.toFloat()))
                    TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                    TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)
                }
            }else{
                AT3VAL.text = ""
                Tax3VAL.text = ""
                ROI3VAL.text = ""
                ROI3P.text = ""
                Profit3VAL.text = ""
                ROI3.setTextColor(Color.parseColor("#C3C3C3"))
                Profit3.setTextColor(Color.parseColor("#C3C3C3"))
                TotalAT(AT1VAL,AT2VAL,AT3VAL,AT4VAL)
                TotalTAX(Tax1VAL,Tax2VAL,Tax3VAL,Tax4VAL)
                TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)
            }

            BuyValue3.addTextChangedListener {
                if(!(SellValue3.text.toString().trim().equals("")) && !(BuyValue3.text.toString().trim().equals("")) && BuyValue3.text.toString() != "." && SellValue3.text.toString() != "." && SellValue3.text.toString() != "0" && BuyValue3.text.toString() != "0"){
                    AfterTax3 = SellValue3.text.toString().toFloat()*0.975
                    Profit3VAL.text = df.format(AfterTax3 - BuyValue3.text.toString().toFloat())
                    ROI3P.text = "%"
                    ROI3VAL.text = df.format(RoiCalc(BuyValue3.text.toString().toFloat(),AfterTax3.toFloat()))
                    AT3VAL.text= df.format(AfterTax3)
                    FontColorApply(3,RoiCalc(BuyValue3.text.toString().toFloat(),AfterTax3.toFloat()))
                    TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                    TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)
                }else{
                    Profit3VAL.text = ""
                    ROI3VAL.text = ""
                    ROI3P.text = ""
                    ROI3.setTextColor(Color.parseColor("#C3C3C3"))
                    Profit3.setTextColor(Color.parseColor("#C3C3C3"))
                    TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                    TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)
                }
            }
        }



        SellValue4.addTextChangedListener {
            if (!(SellValue4.text.toString().trim().equals("")) && SellValue4.text.toString() != "." && SellValue4.text.toString() != "0") {

                AfterTax4 = SellValue4.text.toString().toFloat()*0.975
                AT4VAL.text = df.format(AfterTax4)
                Tax4VAL.text = df.format(SellValue4.text.toString().toFloat()*0.025)
                TotalAT(AT1VAL,AT2VAL,AT3VAL,AT4VAL)
                TotalTAX(Tax1VAL,Tax2VAL,Tax3VAL,Tax4VAL)

                if(!(SellValue4.text.toString().trim().equals("")) && !(BuyValue4.text.toString().trim().equals("")) && !(BuyValue4.text.toString().trim().equals("")) && BuyValue4.text.toString() != "." && BuyValue4.text.toString() != "0"){
                    Profit4VAL.text = df.format(AfterTax4 - BuyValue4.text.toString().toFloat())
                    ROI4VAL.text = df.format(RoiCalc(BuyValue4.text.toString().toFloat(),AfterTax4.toFloat()))
                    ROI4P.text = "%"
                    FontColorApply(4,RoiCalc(BuyValue4.text.toString().toFloat(),AfterTax4.toFloat()))
                    TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                    TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)
                }
            }else{
                AT4VAL.text = ""
                Tax4VAL.text = ""
                ROI4P.text = ""
                ROI4VAL.text = ""
                Profit4VAL.text = ""
                ROI4.setTextColor(Color.parseColor("#C3C3C3"))
                Profit4.setTextColor(Color.parseColor("#C3C3C3"))
                TotalAT(AT1VAL,AT2VAL,AT3VAL,AT4VAL)
                TotalTAX(Tax1VAL,Tax2VAL,Tax3VAL,Tax4VAL)
                TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)

            }

            BuyValue4.addTextChangedListener {
                if(!(SellValue4.text.toString().trim().equals("")) && !(BuyValue4.text.toString().trim().equals("")) && BuyValue4.text.toString() != "." && SellValue4.text.toString() != "." && SellValue4.text.toString() != "0" && BuyValue4.text.toString() != "0"){
                    AfterTax4 = SellValue4.text.toString().toFloat()*0.975
                    Profit4VAL.text = df.format(AfterTax4 - BuyValue4.text.toString().toFloat())
                    ROI4P.text = "%"
                    ROI4VAL.text = df.format(RoiCalc(BuyValue4.text.toString().toFloat(),AfterTax4.toFloat()))
                    AT4VAL.text= df.format(AfterTax4)
                    FontColorApply(4,RoiCalc(BuyValue4.text.toString().toFloat(),AfterTax4.toFloat()))
                    TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                    TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)
                }else{
                    Profit4VAL.text = ""
                    ROI4VAL.text = ""
                    ROI4P.text = ""
                    ROI4.setTextColor(Color.parseColor("#C3C3C3"))
                    Profit4.setTextColor(Color.parseColor("#C3C3C3"))
                    TotalProfit(Profit1VAL,Profit2VAL,Profit3VAL,Profit4VAL)
                    TotalROI(BuyValue1,BuyValue2,BuyValue3,BuyValue4)
                }
            }
        }

        discordlogo.setOnClickListener{

            val discordinvite: Uri = Uri.parse("https://www.discord.com/invite/k2mDMQaTSN")
            val openinvite = Intent(Intent.ACTION_VIEW, discordinvite)
            startActivity(openinvite)


            /*

            if((BigDecimal(SellValue1.text.toString()).multiply(BigDecimal("0.025"))).toString().substringAfter(".").trimEnd('0').length>2){
                Tax1VAL.text = (BigDecimal(SellValue1.text.toString()).multiply(BigDecimal("0.025"))).setScale(2,RoundingMode.CEILING).toString()
            }else{
                Tax1VAL.text = (BigDecimal(SellValue1.text.toString()).multiply(BigDecimal("0.025"))).toString().trimEnd('0')
            }





             */


        }


    }
}

