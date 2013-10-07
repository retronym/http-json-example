import rx.subjects.{PublishSubject, Subject}
import rx.Subscription
import rx.subscriptions.Subscriptions
import rx.util.functions.Action0
import scala.swing._
import scala.swing.event.ButtonClicked
import rx.lang.scala._
import scala.swing.event.ButtonClicked
import scala.swing.event.ButtonClicked
import scala.swing.Reactions.Reaction

object Swing extends scala.swing.SimpleSwingApplication {

  def top: Frame = new MainFrame {

    title = "Reactive Clicker"

    val button = new Button("click me")
    val label = new Label("...")

    contents = new BoxPanel(Orientation.Vertical) {
      contents += button
      contents += label
      border = swing.Swing.EmptyBorder(top = 30, left = 30, bottom = 10, right = 30)
    }

    listenTo(button)

    var subject = PublishSubject.create[AbstractButton]()

    val clickStream = Observable((observer: Observer[AbstractButton]) => {
      val onClicked: Reaction = {
        case ButtonClicked(b) => {
          observer.onNext(b)
        }
      }
      reactions += onClicked
      import ImplicitFunctionConversions._
      val action: Action0 = scalaFunction0ProducingUnitToAction0(
        () => reactions -= onClicked
      )
      Subscriptions.create(action)
    })

    var clicks: Integer = 0
    val n = 10

    clickStream.take(n).subscribe {
      b =>
        clicks += 1
        label.text = s"clicked ${ clicks }"
    }
  }
}
