package com.example.a19012011117_meetpatel_assignment_2

import com.google.firebase.database.Exclude

data class Collage(
    var name:String? = null,
    var imageUrl:String? = null,
    var description:String? = null,
    @get:Exclude
    @set:Exclude
    var key:String? = null

)