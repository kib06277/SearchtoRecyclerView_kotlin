package com.blueaitech.searchtorecyclerview_kotlin

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var searchView : SearchView
    private var arrayList = ArrayList<String>()
    var mAdapter: RecyclerViewAdapter? = null
    var strings = arrayOf(
        "CRUISER MK. I",
        "CRUISER MK. II",
        "VALENTINE",
        "CRUISER MK. III",
        "CRUISER MK. IV",
        "COVENANTER",
        "CRUSADER",
        "GSR 3301 SETTER",
        "LHMTV",
        "GSOR3301 AVR FS",
        "MANTICORE",
        "MATILDA",
        "CHURCHILL I",
        "CHURCHILL VII",
        "BLACK PRINCE",
        "CAERNARVON",
        "CONQUEROR",
        "SUPER CONQUEROR",
        "CAVALIER",
        "CROMWELL",
        "COMET",
        "CENTURION MK. I",
        "CENTURION MK. 7/1",
        "CENTURION ACTION X",
        "VALENTINE AT",
        "BISHOP",
        "FV304",
        "CRUSADER 5.5-IN. SP",
        "FV207",
        "FV3805",
        "CONQUEROR GUN CARRIAGE",
        "AT 2",
        "ACHILLES",
        "CHALLENGER",
        "CHARIOTEER",
        "FV4004 CONWAY",
        "FV4005 STAGE II",
        "AT 8",
        "AT 7",
        "AT 15",
        "TORTOISE",
        "FV217 BADGER"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        for (element in strings) {
            arrayList.add(element)
        }
        mAdapter = RecyclerViewAdapter(arrayList)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
    }

    /**初始化Toolbar內SearchView的設置 */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.action_search)
        searchView = menuItem.actionView as SearchView
        /**SearchView設置，以及輸入內容後的行動 */
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                /**調用RecyclerView內的Filter方法 */
                mAdapter?.getFilter()?.filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}