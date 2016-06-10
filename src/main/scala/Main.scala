object Application extends App {
    val gt = new GameTable()
    gt.CreateGameEntry("quake3")
    gt.SetSetting("quake3", "opt", "v")
    println(gt.GetSetting("quake3", "opt"))
}
