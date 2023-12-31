package vn.com.kell.vaanh._no_run//import android.annotation.SuppressLint
//import android.view.View
//import android.view.ViewGroup
//import androidx.databinding.ViewDataBinding
//import androidx.lifecycle.LifecycleOwner
//import kotlinx.coroutines.flow.StateFlow
//import vn.com.vti.common.adapter.binding.BindingAdapter
//import vn.com.vti.common.adapter.binding.BindingHolder
//import vn.com.vti.common.adapter.binding.viewContext
//import vn.com.vti.common.bindconverter.format
//import vn.com.vti.common.util.extension.DateTimeXs
//import vn.com.vti.common.util.extension.DateTimeXs.toFirstMillisOfDay
//import vn.com.vti.common.viewmodel.collectLatestOnLifecycle
//import vn.com.vti.yma.R
//import vn.com.vti.yma.databinding.ItemCalendarDayBinding
//import vn.com.vti.yma.databinding.ItemCalendarDayOfWeekBinding
//import vn.com.vti.yma.databinding.LayoutCalendarMonthBinding
//import java.text.DateFormatSymbols
//import java.util.*
//
//class DayOfWeekAdapter(private val itemWidth: Int) : BindingAdapter<String>() {
//
//    private val labels: List<String> by lazy {
//        val origin = DateFormatSymbols(Locale.getDefault()).shortWeekdays
//        val calendar = Calendar.getInstance()
//        val firstWeekDay = calendar.firstDayOfWeek
//        calendar.set(Calendar.DAY_OF_WEEK, firstWeekDay)
//        buildList {
//            repeat(7) {
//                add(origin[calendar[Calendar.DAY_OF_WEEK]])
//                calendar.add(Calendar.DATE, 1)
//            }
//        }
//    }
//
//    override fun getItemCount(): Int = 7
//
//    override fun onCreateHolder(
//        parent: ViewGroup, viewType: Int
//    ): BindingHolder<out ViewDataBinding, String> = Holder(parent)
//
//    override fun getItem(position: Int): String = labels[position]
//
//    private inner class Holder(parent: ViewGroup) :
//        BindingHolder<ItemCalendarDayOfWeekBinding, String>(
//            parent, R.layout.item_calendar_day_of_week
//        ) {
//
//        override fun onCreate() {
//            super.onCreate()
//            val maxSize = viewContext.resources.getDimensionPixelSize(R.dimen.item_calendar_size)
//            if (itemWidth < maxSize) {
//                binder.root.layoutParams.apply {
//                    width = itemWidth
//                    height = itemWidth
//                }
//                binder.root.postInvalidate()
//            }
//        }
//
//        override fun onBind(position: Int, model: String?) {
//            super.onBind(position, model)
//            binder.label.text = model
//        }
//    }
//}
//
//data class CalendarDay(
//    val timeInMillis: Long,
//    val inSelectedMonth: Boolean = true,
//    val dayOfMonth: Int = 1,
//    val isToday: Boolean,
//)
//
//class AutoGenerateDayOfMonthAdapter(private val itemWidth: Int) : BindingAdapter<CalendarDay>() {
//
//    private var pairOfMonthAndYear = 0 to 0
//    private var selectedIndex = -1
//    private val listOfDate = mutableListOf<CalendarDay>()
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setMonthAndYear(month: Int, year: Int) {
//        require(month >= Calendar.JANUARY && month <= Calendar.DECEMBER)
//        require(year >= 1970)
//        if (pairOfMonthAndYear.first == month && pairOfMonthAndYear.second == year) return
//        pairOfMonthAndYear = month to year
//        listOfDate.clear()
//        val calendar = Calendar.getInstance().apply {
//            set(year, month, 1)
//            set(Calendar.DAY_OF_WEEK, firstDayOfWeek)
//            set(Calendar.DAY_OF_WEEK_IN_MONTH, 1)
//            toFirstMillisOfDay()
//            if (get(Calendar.DAY_OF_MONTH) != 1) {
//                add(Calendar.DATE, -7)
//            }
//        }
//        val startTimeInMillis = calendar.timeInMillis
//        val endTimeInMillis = Calendar.getInstance().apply {
//            set(year, month, 1)
//            add(Calendar.MONTH, 1)
//            set(Calendar.DAY_OF_WEEK, firstDayOfWeek)
//            set(Calendar.DAY_OF_WEEK_IN_MONTH, 1)
//            add(Calendar.DATE, -1)
//        }.timeInMillis
//        var current = startTimeInMillis
//        val today = Calendar.getInstance().run {
//            toFirstMillisOfDay()
//            timeInMillis
//        }
//        do {
//            calendar.timeInMillis = current
//            val inSelectedMonth = calendar[Calendar.MONTH] == month
//            val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
//            listOfDate.add(
//                CalendarDay(
//                    current,
//                    inSelectedMonth = calendar[Calendar.MONTH] == month,
//                    dayOfMonth = dayOfMonth,
//                    isToday = inSelectedMonth && today == current
//                )
//            )
//            current += DateTimeXs.DAY
//        } while (current <= endTimeInMillis)
//        selectedIndex = -1
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateHolder(
//        parent: ViewGroup, viewType: Int
//    ): BindingHolder<out ViewDataBinding, CalendarDay> = if (viewType == 1) TodayHolder(parent)
//    else NormalHolder(parent)
//
//    override fun getItem(position: Int): CalendarDay = listOfDate[position]
//
//    override fun getItemViewType(position: Int): Int {
//        return if (getItem(position).isToday) 1 else 0
//    }
//
//    override fun getItemCount(): Int = listOfDate.size
//
//    private open inner class BaseHolder(parent: ViewGroup) :
//        BindingHolder<ItemCalendarDayBinding, CalendarDay>(parent, R.layout.item_calendar_day) {
//
//        override fun onCreate() {
//            super.onCreate()
//            val maxSize = viewContext.resources.getDimensionPixelSize(R.dimen.item_calendar_size)
//            if (itemWidth < maxSize) {
//                binder.root.layoutParams.apply {
//                    width = itemWidth
//                    height = itemWidth
//                }
//                binder.root.postInvalidate()
//            }
//            registerRootViewItemClickEvent { position, _, model ->
//                model ?: return@registerRootViewItemClickEvent
//                if (!model.inSelectedMonth) return@registerRootViewItemClickEvent
//                if (selectedIndex == position) return@registerRootViewItemClickEvent
//                val old = selectedIndex
//                selectedIndex = position
//                if (old >= 0) notifyItemChanged(old)
//                if (selectedIndex >= 0) notifyItemChanged(selectedIndex)
//            }
//        }
//
//        override fun onBind(position: Int, model: CalendarDay?) {
//            super.onBind(position, model)
//            model ?: return
//            binder.apply {
//                label.text = model.dayOfMonth.format()
//                root.isSelected = position == selectedIndex
//                root.isEnabled = model.inSelectedMonth
//            }
//        }
//    }
//
//    private inner class TodayHolder(parent: ViewGroup) : BaseHolder(parent) {
//
//        override fun onCreate() {
//            super.onCreate()
//            binder.dot.visibility = View.VISIBLE
//            binder.label.setTextColor(viewContext.getColorStateList(R.color.item_calendar_dot_selector))
//        }
//    }
//
//    private inner class NormalHolder(parent: ViewGroup) : BaseHolder(parent) {
//
//        override fun onCreate() {
//            super.onCreate()
//            binder.dot.visibility = View.GONE
//            binder.label.setTextColor(viewContext.getColorStateList(R.color.item_calendar_text_selector))
//        }
//    }
//}
//
//fun LayoutCalendarMonthBinding.bind(
//    owner: LifecycleOwner, source: StateFlow<Long>
//) {
//    val width = recDayOfWeek.width / 7
//    if (width == 0) {
//        recDayOfWeek.post {
//            val newWidth = recDayOfWeek.width / 7
//            recDayOfWeek.adapter = DayOfWeekAdapter(newWidth)
//            recDayOfMonth.adapter = AutoGenerateDayOfMonthAdapter(newWidth).also { adapter ->
//                source.collectLatestOnLifecycle(owner) {
//                    val calendar = Calendar.getInstance()
//                    calendar.timeInMillis = it
//                    adapter.setMonthAndYear(calendar[Calendar.MONTH], calendar[Calendar.YEAR])
//                }
//            }
//        }
//    } else {
//        recDayOfWeek.adapter = DayOfWeekAdapter(width)
//        recDayOfMonth.adapter = AutoGenerateDayOfMonthAdapter(width).also { adapter ->
//            source.collectLatestOnLifecycle(owner) {
//                val calendar = Calendar.getInstance()
//                calendar.timeInMillis = it
//                adapter.setMonthAndYear(calendar[Calendar.MONTH], calendar[Calendar.YEAR])
//            }
//        }
//    }
//}