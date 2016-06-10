import scala.collection

package object typedefs {
  type GameID = String
}

  object QueryStatus extends Enumeration {
      type QueryStatus = Value
      val Empty, Ready, Working, Error = Value
  }

  class GameEntry {
    def Status = QueryStatus.Empty
    def Settings = new collection.mutable.HashMap[String, String]()
  }

  class GameTable {
    var t = new collection.mutable.HashMap[typedefs.GameID, GameEntry]

    def CreateGameEntry(id: typedefs.GameID): Unit = synchronized {
      t.put(id, new GameEntry())
    }

    def RemoveGameEntry(id: typedefs.GameID): Unit = synchronized {
      t.remove(id)
    }

    def GetSetting(id: typedefs.GameID, k: String): String = synchronized {
        val entry = t(id)

        return entry.Settings(k)
    }

    def SetSetting(id: typedefs.GameID, k: String, v: String): Unit = synchronized {
      val entry = t(id)
      entry.Settings(k)
      entry.Settings(k) = v
      t(id) = entry
    }

    def RemoveSetting(id: typedefs.GameID, k: String): Unit = synchronized {
        val entry = t(id)
        entry.Settings(k)
        entry.Settings -= k
        t(id) = entry
    }

    def SettingKeys(id: typedefs.GameID): collection.Set[String] = synchronized {
        val entry = t(id)
        return entry.Settings.keySet
    }

  }
