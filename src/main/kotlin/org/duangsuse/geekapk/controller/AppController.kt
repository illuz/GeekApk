package org.duangsuse.geekapk.controller

import org.duangsuse.geekapk.AppId
import org.duangsuse.geekapk.AppSize
import org.duangsuse.geekapk.CategoryId
import org.duangsuse.geekapk.UserId
import org.duangsuse.geekapk.entity.App
import org.duangsuse.geekapk.helper.ApiDoc
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("app")
class AppController {
  @GetMapping
  @ResponseBody
  fun apiHint(hsr: HttpServletRequest) = ApiDoc.app(hsr).second

  @GetMapping("/app/{aid}")
  @ResponseBody
  fun readApp(@PathVariable("aid") aid: AppId): App {
    TODO()
  }


  @PutMapping("/app/{aid}")
  @ResponseBody
  fun updateApp(@PathVariable("aid") aid: AppId, @RequestParam("attr") attr: String/* Maybe package or icon or name or screenshots or readme */,
                @RequestBody value: String): Map<String, String> /* attr: String *//* oldVal: String */ {
    TODO()
  }


  @PostMapping("/app")
  @ResponseBody
  fun createApp(@RequestParam("package") packageName: String, @RequestParam("category") category: CategoryId): App {
    TODO()
  }


  @GetMapping("/app/package/{package}")
  @ResponseBody
  fun findAppWithPackageName(@PathVariable("package") packageName: String): App {
    TODO()
  }


  @GetMapping("/app/all")
  @ResponseBody
  fun listApp(@RequestParam(name = "inCategory", required = false) inCategory: CategoryId?,
              @RequestParam(name = "sort", required = false) sort: String?/* Maybe updated or comments or stars or created */,
              @RequestParam(name = "sliceFrom", required = false) sliceFrom: AppSize?, @RequestParam(name = "sliceTo", required = false) sliceTo: AppSize?): List<App> {
    TODO()
  }


  @DeleteMapping("/app/{aid}")
  @ResponseBody
  fun dropApp(@PathVariable("aid") aid: AppId): App {
    TODO()
  }


  @GetMapping("/app/search/{content}")
  fun searchApp(@RequestParam(name = "inCategory", required = false) inCategory: CategoryId?, @PathVariable("content") content: String,
                @RequestParam("type") type: String/* Maybe name or package or icon or readme */,
                @RequestParam(name = "sort", required = false) sort: String?/* Maybe updated or comments or stars or created */) {
    TODO()
  }


  @PostMapping("/app/{aid}/collab")
  fun addCollab(@RequestParam("uid") uid: UserId, @PathVariable("aid") aid: AppId) {
    TODO()
  }


  @DeleteMapping("/app/{aid}/collab")
  fun removeCollab(@RequestParam("uid") uid: UserId, @PathVariable("aid") aid: AppId) {
    TODO()
  }


  @GetMapping("/app/collaborators/{aid}")
  @ResponseBody
  fun collaborators(@PathVariable("aid") aid: AppId): List<Int> {
    TODO()
  }


  @GetMapping("/app/collaborated/{uid}")
  @ResponseBody
  fun collaborated(@PathVariable("uid") uid: UserId): List<Int> {
    TODO()
  }
}
