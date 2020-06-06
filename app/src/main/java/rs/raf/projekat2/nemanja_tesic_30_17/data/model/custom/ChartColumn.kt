package rs.raf.projekat2.nemanja_tesic_30_17.data.model.custom

data class ChartColumn(
    val dateCreated: String,
    var count: Int
): Comparable<ChartColumn> {
    override fun compareTo(other: ChartColumn): Int {
        return dateCreated.compareTo(other.dateCreated)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChartColumn

        if (dateCreated != other.dateCreated) return false

        return true
    }

    override fun hashCode(): Int {
        return dateCreated.hashCode()
    }


}