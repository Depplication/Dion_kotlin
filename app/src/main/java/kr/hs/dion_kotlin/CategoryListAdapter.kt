package kr.hs.dion_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView

class CategoryListAdapter(
    private val context: Context,
    private val parentList: MutableList<CategoryData>,
    private val childList: MutableList<MutableList<CategoryData>>
): BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return parentList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childList[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): Any {
        return parentList[groupPosition]
    }

    override fun getChild(groupPositon: Int, childPosition: Int): Any {
        return childList[groupPositon][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, converView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val parentView = inflater.inflate(R.layout.category_parent_list_item, parent, false)
        val parentCategoryText = parentView.findViewById<TextView>(R.id.parent_text)
        val parentCategoryImg = parentView.findViewById<ImageView>(R.id.arrow)
        parentCategoryText.text = parentList[groupPosition].Text
        if(parentList[groupPosition].Img != null) {
            parentCategoryImg.setImageResource(parentList[groupPosition].Img!!)
        }
        setArrow(groupPosition, parentView, isExpanded)

        return parentView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, converView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val childView = inflater.inflate(R.layout.category_child_list_item, parent, false)
        val childCategoryText = childView.findViewById<TextView>(R.id.child_text)
        val childCategoryImg = childView.findViewById<ImageView>(R.id.child_img)
        childCategoryText.text = childList[groupPosition][childPosition].Text
        if(childList[groupPosition][childPosition].Img != null) {
            childCategoryImg.setImageResource(childList[groupPosition][childPosition].Img!!)
        }
        return childView
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    private fun setArrow(parentPosition: Int, parentView: View, isExpanded: Boolean) {

        if (parentPosition != 1) {
            if (isExpanded) parentView.findViewById<ImageView>(R.id.arrow).setImageResource(R.drawable.under_arrow)
            else parentView.findViewById<ImageView>(R.id.arrow).setImageResource(R.drawable.forward_arrow)
        }
    }

}