fetch('/static/gis/data/xz_84.json').then(res => res.json()).then(res => {
    data = res
    f=new turf.featureCollection()
    f.features=[]
    data.features.forEach((i, index) => {
        console.log(index);
        if (i.geometry.type == "MultiPolygon") {
            var list = i.geometry.coordinates
            var tmpArea = 0
            var tmpIndex=0
            for (var j = 0; j < list.length; j++) {
                p = new turf.polygon(i.geometry.coordinates[j], i.properties)
                area=turf.area(p)
                if(area>tmpArea){
                    tmpIndex=j
                }
                p = new turf.polygon(i.geometry.coordinates[tmpIndex], i.properties)
            }
        } else {
            p = new turf.polygon(i.geometry.coordinates, i.properties)
        }
        pp = turf.centerOfMass(p)
        pp.properties = p.properties
        f.features.push(pp)
    })
})