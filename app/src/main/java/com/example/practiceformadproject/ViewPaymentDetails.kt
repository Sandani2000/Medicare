package com.example.practiceformadproject
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
//import com.example.myapplication1.R
//import com.example.myapplication1.models.PaymentModel
import com.google.firebase.database.FirebaseDatabase

class ViewPaymentDetails : AppCompatActivity() {


    private lateinit var tvCardNumber : TextView
    private lateinit var tvCardHolderName : TextView
    private lateinit var tvMonthYear : TextView
    private lateinit var btnUpdate : Button
    private lateinit var btnDelete : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpaymentdetails)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("payId").toString(),
                intent.getStringExtra("payCardNumber").toString()

            )
        }
        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("payId").toString()
            )
        }
    }
    private fun deleteRecord(
        id :String
    )
    {
        val dbRef = FirebaseDatabase.getInstance().getReference("Payments").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Payments data deleted!",Toast.LENGTH_LONG).show()

            val intent = Intent(this,PaymentFetching::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error->
            Toast.makeText(this,"Deleting ERR ${error.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvCardNumber = findViewById(R.id.cardNo)
        tvCardHolderName = findViewById(R.id.holderName)
        tvMonthYear = findViewById(R.id.expDate)
        
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {

        tvCardNumber.text= intent.getStringExtra("etCardNumber")
        tvCardHolderName.text= intent.getStringExtra("etCardHolderName")
        tvMonthYear.text= intent.getStringExtra("etMonthYear")


    }

    private fun openUpdateDialog(
        payId:String,
        payCardNumber:String
    )
    {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_dialog,null)

        mDialog.setView(mDialogView)

        val etCardNumber = mDialogView.findViewById<EditText>(R.id.cardNo)
        val etCardHolderName = mDialogView.findViewById<EditText>(R.id.holderName)
        val etMonthYear = mDialogView.findViewById<EditText>(R.id.expDate)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etCardNumber.setText(intent.getStringExtra("payCardNumber").toString())
        etCardHolderName.setText(intent.getStringExtra("payCardHolderName").toString())
        etMonthYear.setText(intent.getStringExtra("payMonthYear").toString())


        mDialog.setTitle("Updating $payCardNumber Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updatePayData(
                payId,
                etCardNumber.text.toString(),
                etCardHolderName.text.toString(),
                etMonthYear.text.toString()
            )

            Toast.makeText(applicationContext,"Payment data Updated",Toast.LENGTH_LONG).show()

            //we are setting updated data to  our textviews

            tvCardNumber.text= etCardNumber.text.toString()
            tvCardHolderName.text=etCardHolderName.text.toString()
            tvMonthYear.text=etMonthYear.text.toString()


            alertDialog.dismiss()
        }


    }
    private fun updatePayData(
        payId:String,
        cardNo:String,
        holderName:String,
        expDate:String

    )
    {
        val dbRef = FirebaseDatabase.getInstance().getReference("Payments").child(payId)    //we are not getting reference to the hall data base
        val payinfo = PaymentModel(payId,cardNo,holderName,expDate)
        dbRef.setValue(payinfo)

    }

}
