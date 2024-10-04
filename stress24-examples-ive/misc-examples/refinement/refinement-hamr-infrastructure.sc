// #Sireum #Logika
import org.sireum._

// based on https://github.com/santoslab/logika-overview-case-studies/blob/main/Hamr-R/HAMR-R.sc

object Art {

  @sig trait DataContent

  object DataContent {
    @datatype class None extends DataContent

    @datatype class Other extends DataContent
  }

  @range(min = 0) class PortId

  val numOfPortIds: Z = halt("Unbounded")

  @strictpure def uniquePortIds(s: ISZ[PortId]): B = ∀(s.indices)(i => ∀(s.indices)(j => (i != j) ->: (s(i) != s(j))))

  object ExecSemantics {

    object PortProperties {

      @enum object Kind {
        "Event"
        "Data"
      }

    }

    object PortQueue {
      type Type = ISZ[DataContent]

      @strictpure def empty: Type = ISZ()
    }

    type PortStates = IndexMap[PortId, PortQueue.Type]

    object Util {

      object ReceiveInput {
        @strictpure def behavior(iinPre: PortStates, ainPre: PortStates, kind: ExecSemantics.PortProperties.Kind.Type,
                                 portId: PortId, iinPost: PortStates, ainPost: PortStates): B =
          (iinPre.contains(portId) & ainPre.contains(portId)) -->: ((iinPre.get(portId).get.size == 1) & (
            ((kind == ExecSemantics.PortProperties.Kind.Data) ->:
              (ainPost ≡ (ainPre + portId ~> iinPre.get(portId).get) & iinPre ≡ iinPost)) &
              ((kind == ExecSemantics.PortProperties.Kind.Event) ->:
                iinPost ≡ (iinPre + portId ~> PortQueue.empty) & ainPost ≡ (ainPre + portId ~> iinPre.get(portId).get))))
      }

      @strictpure def inv(iin: PortStates, ain: PortStates, numOfPorts: Z): B =
        iin.size == numOfPorts & ain.size == numOfPorts
    }

    @strictpure def receive_input_event_port_dequeue_policy(iin_port: PortQueue.Type): (PortQueue.Type, PortQueue.Type) = {
      val iin_port_new = PortQueue.empty
      val pin_port_new = iin_port
      (iin_port_new, pin_port_new)
    }

    @pure def receiveInputOnePortSemantics(kind: ExecSemantics.PortProperties.Kind.Type,
                                           portId: PortId, iin: PortStates, ain: PortStates): (PortStates, PortStates) = {
      Contract(
        Requires(
          Util.inv(iin, ain, numOfPortIds),
          iin.contains(portId),
          iin.get(portId).get.size == 1
        ),
        Ensures(
          Util.ReceiveInput.behavior(iin, ain, kind, portId, Res[(PortStates, PortStates)]._1, Res[(PortStates, PortStates)]._2)
        )
      )
      kind match {
        case ExecSemantics.PortProperties.Kind.Data =>
          val iin_new: PortStates = iin
          val queueValue = iin.get(portId).get
          assert(queueValue.size == 1)
          val ain_new: PortStates = ain + portId ~> queueValue
          return (iin_new, ain_new)
        case ExecSemantics.PortProperties.Kind.Event =>
          val iin_portId_queue = iin.get(portId).get
          val (iin_portId_queue_new, ain_portId_queue_new) = receive_input_event_port_dequeue_policy(iin_portId_queue)
          val iin_new = iin + portId ~> iin_portId_queue_new
          val ain_new = ain + portId ~> ain_portId_queue_new
          return (iin_new, ain_new)
      }
    }
  }

  object Impl {

    type PortContents = MS[Art.PortId, DataContent]

    val inInfrastructurePorts: PortContents = MS.create(numOfPortIds, Art.DataContent.None())
    val inApplicationPorts: PortContents = MS.create(numOfPortIds, Art.DataContent.None())
    val outApplicationPorts: PortContents = MS.create(numOfPortIds, Art.DataContent.None())
    val outInfrastructurePorts: PortContents = MS.create(numOfPortIds, Art.DataContent.None())

    @strictpure def inv(inInfra: PortContents, inApp: PortContents, numOfPortIds: Z): B =
      inInfra.size ≡ numOfPortIds & inApp.size ≡ numOfPortIds

    object Spec {

      object ReceiveInput {

        @strictpure def eventNonEmpty(inInfraPre: PortContents, inAppPre: PortContents,
                                      isEventPort: B, portId: PortId,
                                      inInfraPost: PortContents, inAppPost: PortContents): B =
          inInfraPre.isInBound(portId) -->:
            isEventPort ->:
            !inInfraPre(portId).isInstanceOf[DataContent.None] ->:
            (inInfraPost ≡ inInfraPre(portId ~> Art.DataContent.None()) & inAppPost ≡ inAppPre(portId ~> inInfraPre(portId)))

        @strictpure def eventEmpty(inInfraPre: PortContents, inAppPre: PortContents,
                                   isEventPort: B, portId: PortId,
                                   inInfraPost: PortContents, inAppPost: PortContents): B =
          inInfraPre.isInBound(portId) -->:
            isEventPort ->:
            inInfraPre(portId).isInstanceOf[DataContent.None] ->:
            (inAppPost ≡ inAppPre(portId ~> DataContent.None()) & inInfraPre ≡ inInfraPost)


        @strictpure def dataNonEmpty(inInfraPre: PortContents, inAppPre: PortContents,
                                     isEventPort: B, portId: PortId,
                                     inInfraPost: PortContents, inAppPost: PortContents): B =
          inInfraPre.isInBound(portId) -->:
            !isEventPort ->:
            !inInfraPre(portId).isInstanceOf[DataContent.None] ->:
            (inInfraPost ≡ inInfraPre & inAppPost ≡ inAppPre(portId ~> inInfraPre(portId)))

        @strictpure def dataEmpty(inInfraPre: PortContents, inAppPre: PortContents,
                                  isEventPort: B, portId: PortId,
                                  inInfraPost: PortContents, inAppPost: PortContents): B =
          (inInfraPre.isInBound(portId) -->:
            !isEventPort ->:
            inInfraPre(portId).isInstanceOf[DataContent.None] ->:
            (inInfraPre ≡ inInfraPost & inAppPre ≡ inAppPost))

        @strictpure def behavior(inInfraPre: PortContents, inAppPre: PortContents,
                                 isEventPort: B, portId: PortId,
                                 inInfraPost: PortContents, inAppPost: PortContents): B =
          eventNonEmpty(inInfraPre, inAppPre, isEventPort, portId, inInfraPost, inAppPost) &
            eventEmpty(inInfraPre, inAppPre, isEventPort, portId, inInfraPost, inAppPost) &
            dataNonEmpty(inInfraPre, inAppPre, isEventPort, portId, inInfraPost, inAppPost) &
            dataEmpty(inInfraPre, inAppPre, isEventPort, portId, inInfraPost, inAppPost)
      }
    }

    def receiveInputPort(isEventPort: B, portId: PortId): Unit = {
      Contract(
        Requires(
          inv(inInfrastructurePorts, inApplicationPorts, numOfPortIds),
          inInfrastructurePorts.isInBound(portId)
        ),
        Modifies(inInfrastructurePorts, inApplicationPorts),
        Ensures(
          Spec.ReceiveInput.behavior(In(inInfrastructurePorts), In(inApplicationPorts), isEventPort, portId,
            inInfrastructurePorts, inApplicationPorts)
        )
      )
      val m = inInfrastructurePorts(portId)
      if (!m.isInstanceOf[DataContent.None]) {
        inApplicationPorts(portId) = m
        if (isEventPort) {
          inInfrastructurePorts(portId) = DataContent.None()
        }
      } else if (isEventPort) {
        inApplicationPorts(portId) = DataContent.None()
      }
    }
  }

  object Refinement {

    @helper
    @strictpure def portStatesContent(isEvent: B, portId: PortId, ps: ExecSemantics.PortStates, d: DataContent): B = {
      ps.contains(portId) -->: (
        (isEvent ->:
          ((d.isInstanceOf[DataContent.None] ≡ (ps.get(portId) ≡ Some(ISZ[DataContent]()))) &
            (!d.isInstanceOf[DataContent.None] ≡ (ps.get(portId) ≡ Some(ISZ(d)))))) &
          (!isEvent ->: (ps.get(portId) ≡ Some(ISZ(d))))
        )
    }

    @helper
    @strictpure def portIdQueueContent(isEvent: B, portId: PortId, ps: ExecSemantics.PortStates, pc: Impl.PortContents): B =
      pc.isInBound(portId) && portStatesContent(isEvent, portId, ps, pc(portId))

    @helper
    @strictpure def portStatesContents(inInfraSem: ExecSemantics.PortStates, inAppSem: ExecSemantics.PortStates,
                                       inInfraImpl: Impl.PortContents, inAppImpl: Impl.PortContents,
                                       isEvent: B, portId: PortId): B =
      portIdQueueContent(isEvent, portId, inInfraSem, inInfraImpl) &
        portIdQueueContent(isEvent, portId, inAppSem, inAppImpl)

    @pure def receiveInputOne(isEvent: B, kind: ExecSemantics.PortProperties.Kind.Type, portId: PortId,
                              inInfraSemPre: ExecSemantics.PortStates, inAppSemPre: ExecSemantics.PortStates,
                              inInfraSemPost: ExecSemantics.PortStates, inAppSemPost: ExecSemantics.PortStates,
                              inInfraImplPre: Impl.PortContents, inAppImplPre: Impl.PortContents,
                              inInfraImplPost: Impl.PortContents, inAppImplPost: Impl.PortContents): Unit = {
      Contract(
        Requires(
          ExecSemantics.Util.inv(inInfraSemPre, inAppSemPre, numOfPortIds),
          Impl.inv(inInfraImplPre, inAppImplPre, numOfPortIds),
          inInfraSemPre.contains(portId),
          !isEvent ->: !inInfraImplPre(portId).isInstanceOf[DataContent.None],
          isEvent == (kind == ExecSemantics.PortProperties.Kind.Event),
          ExecSemantics.Util.ReceiveInput.behavior(inInfraSemPre, inAppSemPre, kind, portId, inInfraSemPost, inAppSemPost),
          Impl.Spec.ReceiveInput.behavior(inInfraImplPre, inAppImplPre, isEvent, portId, inInfraImplPost, inAppImplPost),
          portStatesContents(inInfraSemPre, inAppSemPre, inInfraImplPre, inAppImplPre, isEvent, portId)
        ),
        Ensures(
          portStatesContents(inInfraSemPost, inAppSemPost, inInfraImplPost, inAppImplPost, isEvent, portId)
        )
      )
    }
  }
}
