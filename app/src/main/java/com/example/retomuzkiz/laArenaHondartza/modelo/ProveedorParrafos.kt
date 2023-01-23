package com.example.retomuzkiz.laArenaHondartza.modelo

import android.content.res.Resources
import com.example.retomuzkiz.R

class ProveedorParrafos{
    companion object{
        var listaParrafos= listOf(
            Parrafo(
                Resources.getSystem().getString(R.string.arenaExpl1)
            ),
            Parrafo(
                Resources.getSystem().getString(R.string.arenaExpl2)
            ),
            Parrafo(
                Resources.getSystem().getString(R.string.arenaExpl3)
            ),
            Parrafo(
                Resources.getSystem().getString(R.string.arenaExpl4)
            ),
            Parrafo(
                Resources.getSystem().getString(R.string.arenaExpl5)
            )
        )
    }
}