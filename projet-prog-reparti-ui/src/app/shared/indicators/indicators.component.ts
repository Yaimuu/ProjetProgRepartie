import {Component, Inject, Input, OnInit} from "@angular/core";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { ApiService } from "../../core/services/api.service";

@Component({
  selector: "app-indicators",
  templateUrl: "./indicators.component.html",
  styleUrls: ['./indicators.component.css']
})
export class IndicatorsComponent implements OnInit {

  simulation: any;
  simulationName: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              protected apiService: ApiService) { }

  ngOnInit(): void {
    this.simulation = this.data.simulation;
    this.simulationName = this.data.simulationName;
  }

  getColor(indicator: any){
      const checked = indicator.checked;
      let value = checked ? indicator.valueIfCheck : indicator.valueIfUnCheck;
      if (value > 0) {
        return "green-indicator";
      } else {
        return "red-indicator";
      }
  }


}

