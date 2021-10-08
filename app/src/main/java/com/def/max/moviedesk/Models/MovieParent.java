package com.def.max.moviedesk.Models;


import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class MovieParent extends ExpandableGroup<MovieChild>
{
    public MovieParent(String title, List<MovieChild> items)
    {
        super(title, items);
    }
}
