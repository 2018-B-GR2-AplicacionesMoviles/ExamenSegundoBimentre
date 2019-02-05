package com.example.examenmoviles2do

import android.os.Parcel
import android.os.Parcelable

class Comida(
    var nombrePlato: String,
    var descripcionPlato: String,
    var nacionalidad: String,
    var numeroPersonas: Int,
    var picante: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(

        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombrePlato)
        parcel.writeString(descripcionPlato)
        parcel.writeString(nacionalidad)
        parcel.writeInt(numeroPersonas)
        parcel.writeString(picante)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comida> {
        override fun createFromParcel(parcel: Parcel): Comida {
            return Comida(parcel)
        }

        override fun newArray(size: Int): Array<Comida?> {
            return arrayOfNulls(size)
        }
    }


}

